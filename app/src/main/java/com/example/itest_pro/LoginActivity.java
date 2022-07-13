package com.example.itest_pro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itest_pro.Model.LogInData;
import com.example.itest_pro.Model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itest_pro.databinding.ActivityMainBinding;
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

public class LoginActivity extends AppCompatActivity {
    EditText editUserName, editPass;
    Button btnLogin;
//    public static final String LOGIN = "LOGIN";
//    public static final String BASE_URL = "http://192.168.1.249:8080/DOAN_ITESTPRO/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserName = findViewById(R.id.input_username);
        editPass = findViewById(R.id.input_pass);
        btnLogin = findViewById(R.id.btn_login);

        OkHttpClient client = new OkHttpClient();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestBody formBody = new FormBody.Builder()
                        .add("username", String.valueOf(editUserName.getText()))
                        .add("password", String.valueOf(editPass.getText()))
                        .build();

                Request request = new Request.Builder()
                        .url(MainActivity.BASE_URL + "rest/login")
                        .post(formBody)
                        .build();

                Moshi moshi = new Moshi.Builder().build();
                
                Type usersType = Types.newParameterizedType(LogInData.class);
                final JsonAdapter<LogInData> jsonAdapter = moshi.adapter(usersType);

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

                        final LogInData logInData = jsonAdapter.fromJson(json);

                        // Cho hiển thị lên RecyclerView.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (logInData.getIslogin() == false) {
                                    Toast.makeText(getApplicationContext(), "Tên hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                } else {
                                    SharedPreferences.Editor editor = getSharedPreferences(MainActivity.LOGIN, MODE_PRIVATE).edit();
                                    editor.putBoolean("islogin", logInData.getIslogin());
                                    editor.putString("tenhocvien", logInData.getUser().getFullname());
                                    editor.putInt("hocvienid", logInData.getUser().getId());

                                    editor.apply();
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(main);
                                }


                            }
                        });
                    }
                });
            }
        });
    }
}