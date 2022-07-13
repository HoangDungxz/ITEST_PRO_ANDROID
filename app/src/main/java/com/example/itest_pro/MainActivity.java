package com.example.itest_pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itest_pro.Model.CauHoiData;
import com.example.itest_pro.Model.CuocThi;
import com.example.itest_pro.Model.DanhSachKetQua;
import com.example.itest_pro.Model.LogInData;
import com.example.itest_pro.adapter.AdapterCuocThi;
import com.example.itest_pro.adapter.AdapterDanhSachKetQua;
import com.example.itest_pro.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static final String LOGIN = "LOGIN";
    public static final String BASE_URL = "http://192.168.1.249:8080/DOAN_ITESTPRO/";
    public static CauHoiData cauHoiData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(LOGIN, MODE_PRIVATE);

        Boolean isLogin = prefs.getBoolean("islogin", false);

        if (isLogin == false) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        String str_tenhocvien = prefs.getString("tenhocvien", "");
        String str_sv_email = prefs.getString("email", "");

        OkHttpClient client = new OkHttpClient();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);


        TextView tenhocvien = findViewById(R.id.tenhocvien);
        tenhocvien.setText(str_tenhocvien);

        TextView sv_email = findViewById(R.id.sv_email);
        sv_email.setText(str_sv_email);

        Integer hocvienid = prefs.getInt("hocvienid", 0);

        Request request = new Request.Builder()
                .url(BASE_URL + "rest/thi/lichthi?hocvienid=" + hocvienid)
                .build();

        Moshi moshi = new Moshi.Builder().build();
        Type cuocThiType = Types.newParameterizedType(List.class, CuocThi.class);
        final JsonAdapter<List<CuocThi>> jsonAdapter = moshi.adapter(cuocThiType);

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();

                final List<CuocThi> cuocThiList = jsonAdapter.fromJson(json);

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTitle(str_tenhocvien);
                        AdapterCuocThi adapterCuocThi = new AdapterCuocThi(MainActivity.this, cuocThiList);
                        ListView listView = findViewById(R.id.cuocthi);
                        listView.setAdapter(adapterCuocThi);
                    }
                });
            }
        });

        Request laydanhsachketqua = new Request.Builder()
                .url(BASE_URL + "rest/thi/danhsachketqua?hocvienid=" + hocvienid)
                .build();

        Type danhSachKqType = Types.newParameterizedType(List.class, DanhSachKetQua.class);
        final JsonAdapter<List<DanhSachKetQua>> jsonAdapterdanhSachKq = moshi.adapter(danhSachKqType);

        client.newCall(laydanhsachketqua).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();

                final List<DanhSachKetQua> ds = jsonAdapterdanhSachKq.fromJson(json);


                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AdapterDanhSachKetQua adapterDanhSachKetQua = new AdapterDanhSachKetQua(MainActivity.this, ds);
                        ListView listView = findViewById(R.id.danhsachketqua);
                        listView.setAdapter(adapterDanhSachKetQua);
                    }
                });
            }
        });

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getSharedPreferences(LOGIN, MODE_PRIVATE).edit();
                editor.putBoolean("islogin", false);
                editor.apply();
                finish();
                startActivity(getIntent());
                return;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getSharedPreferences(LOGIN, MODE_PRIVATE).edit();
                editor.putBoolean("islogin", false);
                editor.apply();
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
