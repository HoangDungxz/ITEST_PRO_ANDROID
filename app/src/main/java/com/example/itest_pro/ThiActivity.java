package com.example.itest_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itest_pro.Model.CauHoiData;
import com.example.itest_pro.Model.Cauhoi;
import com.example.itest_pro.Model.CuocThi;
import com.example.itest_pro.Model.DapAn;
import com.example.itest_pro.Model.LogInData;
import com.example.itest_pro.Model.guiketqua.CacCauHoiDuocLuaChon;
import com.example.itest_pro.Model.guiketqua.CauHoiDuocLuaChon;
import com.example.itest_pro.Model.guiketqua.DapAnDuocLuaChon;
import com.example.itest_pro.adapter.AdapterCauHoi;
import com.example.itest_pro.adapter.AdapterCuocThi;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ThiActivity extends AppCompatActivity {
//    public static final String BASE_URL = "http://192.168.1.249:8080/DOAN_ITESTPRO/";
//    public static final String LOGIN = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatActivity self = this;

        Bundle b = getIntent().getExtras();
        OkHttpClient client = new OkHttpClient();
        Integer cuocthiid = null;
        if (b != null) {
            cuocthiid = (Integer) b.get("id");
        }


        SharedPreferences prefs = getSharedPreferences(MainActivity.LOGIN, MODE_PRIVATE);
        Integer hocvienid = prefs.getInt("hocvienid", 0);

        Request request = new Request.Builder()
                .url(MainActivity.BASE_URL + "rest/thi/tracnghiem?hocvienid=" + hocvienid + "&cuocthiid=" + cuocthiid)
                .build();

        Moshi moshi = new Moshi.Builder().build();
        Type cauHoiType = Types.newParameterizedType(CauHoiData.class);
        final JsonAdapter<CauHoiData> jsonAdapter = moshi.adapter(cauHoiType);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                MainActivity.cauHoiData = null;
                try {
                    MainActivity.cauHoiData = jsonAdapter.fromJson(json);
                } catch (Exception e) {
                    if (MainActivity.cauHoiData == null || MainActivity.cauHoiData.getListCauHoi().size() <= 0) {
                        Intent intent = new Intent(self, MainActivity.class);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Bạn không được thi", Toast.LENGTH_LONG).show();
                            }
                        });

                        startActivity(intent);
                        return;
                    }
                }


                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        AdapterCauHoi adapterCauHoi = new AdapterCauHoi(self, ThiActivity.this, MainActivity.cauHoiData.getListCauHoi());
                        ListView listView = self.findViewById(R.id.show_cauhoi);
                        listView.setAdapter(adapterCauHoi);
                        setTitle(MainActivity.cauHoiData.getTenmon() + " - " + MainActivity.cauHoiData.getTenbode());
                        TextView timer = self.findViewById(R.id.cauhoi_clock);

                        initCountDownTimer(Integer.parseInt(MainActivity.cauHoiData.getThoigianlambai()) * 60 * 1000, timer);

                        Button submit_thi = self.findViewById(R.id.submit_thi);
                        submit_thi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                CauHoiData c = MainActivity.cauHoiData;

//                                Intent intent = new Intent(self, MainActivity.class);
//                                startActivity(intent);
                                CacCauHoiDuocLuaChon cacCauHoiDuocLuaChon = new CacCauHoiDuocLuaChon();

                                List<CauHoiDuocLuaChon> cauHoiDuocLuaChonList = new ArrayList<>();

                                for (Cauhoi cauhoi : c.getListCauHoi()) {
                                    CauHoiDuocLuaChon cauHoiDuocLuaChon = new CauHoiDuocLuaChon();
                                    cauHoiDuocLuaChon.setCauhoiid(cauhoi.getId());

                                    List<DapAnDuocLuaChon> dapAnList = new ArrayList<>();
                                    for (DapAn d : cauhoi.getCacdapan()) {
                                        DapAnDuocLuaChon dapAnDuocLuaChon = new DapAnDuocLuaChon();
                                        dapAnDuocLuaChon.setDapanid(d.getId());
                                        if (d.isChecked() == true) {
                                            dapAnList.add(dapAnDuocLuaChon);
                                        }
                                    }
                                    cauHoiDuocLuaChon.setCacdapanduocluachon(dapAnList);
                                    cauHoiDuocLuaChonList.add(cauHoiDuocLuaChon);
                                }

                                cacCauHoiDuocLuaChon.setCauhoiduocluachon(cauHoiDuocLuaChonList);

                                String idbode = MainActivity.cauHoiData.getIdbode().toString();
                                String idcuocthi = MainActivity.cauHoiData.getCuocthiid().toString();
                                String sophutlam = "10";
                                String thoigianlambai = MainActivity.cauHoiData.getThoigianlambai();


                                Moshi moshi = new Moshi.Builder().build();

                                Type usersType = Types.newParameterizedType(CacCauHoiDuocLuaChon.class);
                                final JsonAdapter<CacCauHoiDuocLuaChon> jsonAdapter = moshi.adapter(usersType);

                                String json = jsonAdapter.toJson(cacCauHoiDuocLuaChon);

                                RequestBody formBody = new FormBody.Builder()
                                        .add("caccauhoiduocluachon_str", String.valueOf(json))
                                        .add("idbode", String.valueOf(idbode))
                                        .add("idcuocthi", String.valueOf(idcuocthi))
                                        .add("sophutlam", String.valueOf(sophutlam))
                                        .add("thoigianlambai", String.valueOf(thoigianlambai))
                                        .add("idsinhvien", String.valueOf(hocvienid))
                                        .build();

                                Request request = new Request.Builder()
                                        .url(MainActivity.BASE_URL + "rest/thi/guiketqua")
                                        .post(formBody)
                                        .build();
                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Toast.makeText(getApplicationContext(), "Nộp bài thất bại", Toast.LENGTH_LONG).show();
                                        Log.e("Error", "Network Error");
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                                        String json = response.body().string();

                                        // Cho hiển thị lên RecyclerView.
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(self, MainActivity.class);
                                                Toast.makeText(getApplicationContext(), "Nộp bài thành công", Toast.LENGTH_LONG).show();
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });


                            }
                        });
                    }
                });
            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi);

    }

    public void initCountDownTimer(int time, TextView textView) {
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                String second = under60((int) (millisUntilFinished / 1000));
                String minute = under60((int) millisUntilFinished / 60 / 1000);

                textView.setText(minute.toString() + ":" + second.toString());
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();
    }


    private String under60(Integer time) {
        if (time > 60) {
            time = time % 60;
        }
        return twoNumber(time);
    }

    private String twoNumber(Integer time) {
        if (time.toString().length() <= 1) {
            return "0" + time.toString();
        }
        return time.toString();
    }
}