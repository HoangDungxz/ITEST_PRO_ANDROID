package com.example.itest_pro.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.itest_pro.Model.CuocThi;
import com.example.itest_pro.Model.DanhSachKetQua;
import com.example.itest_pro.R;
import com.example.itest_pro.ThiActivity;

import java.util.List;


public class AdapterDanhSachKetQua extends ArrayAdapter<DanhSachKetQua> {
    private Context context;
    private List<DanhSachKetQua> danhSachKetQuaList;

    public AdapterDanhSachKetQua(@NonNull Context context, @NonNull List<DanhSachKetQua> danhSachKetQuaList) {
        super(context, R.layout.item_danhsachketqua, danhSachKetQuaList);

        this.context = context;
        this.danhSachKetQuaList = danhSachKetQuaList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_danhsachketqua, null);
        }

        DanhSachKetQua d = danhSachKetQuaList.get(position);

        TextView item_danhsachketqua_monthi = v.findViewById(R.id.item_danhsachketqua_monthi);
        TextView item_danhsachketqua_de = v.findViewById(R.id.item_danhsachketqua_de);
        TextView item_danhsachketqua_ngay = v.findViewById(R.id.item_danhsachketqua_ngay);
        TextView item_danhsachketqua_diem = v.findViewById(R.id.item_danhsachketqua_diem);
        
        item_danhsachketqua_monthi.setText(String.valueOf(d.getMonthi()));
        item_danhsachketqua_de.setText(String.valueOf(d.getBode()));
        item_danhsachketqua_ngay.setText(String.valueOf(d.getThoigianthi()));
        item_danhsachketqua_diem.setText(String.valueOf(d.getDiemthi()));

        return v;
    }
}
