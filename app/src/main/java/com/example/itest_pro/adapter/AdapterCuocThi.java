package com.example.itest_pro.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.itest_pro.LoginActivity;
import com.example.itest_pro.MainActivity;
import com.example.itest_pro.Model.CuocThi;
import com.example.itest_pro.R;
import com.example.itest_pro.ThiActivity;

import java.util.List;


public class AdapterCuocThi extends ArrayAdapter<CuocThi> {
    private Context context;
    private List<CuocThi> cuocThiList;

    public AdapterCuocThi(@NonNull Context context, @NonNull List<CuocThi> cuocThiList) {
        super(context, R.layout.item_cuocthi, cuocThiList);

        this.context = context;
        this.cuocThiList = cuocThiList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_cuocthi, null);
        }

        CuocThi s = cuocThiList.get(position);

        TextView tenCuocThi = v.findViewById(R.id.tencuocthi);
        TextView ngayThi = v.findViewById(R.id.ngaythi);
        TextView giothi = v.findViewById(R.id.giothi);
        TextView phutthi = v.findViewById(R.id.phutthi);

        LinearLayout item_cuocthi = v.findViewById(R.id.item_cuocthi);

        tenCuocThi.setText(String.valueOf(s.getMonthi()));
        ngayThi.setText(String.valueOf(s.getNgaythi()));
        giothi.setText(String.valueOf(s.getGiothi()));
        phutthi.setText(String.valueOf(s.getPhutthi()));

        item_cuocthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThiActivity.class);
                int id = cuocThiList.get(position).getId();
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
        return v;
    }
}
