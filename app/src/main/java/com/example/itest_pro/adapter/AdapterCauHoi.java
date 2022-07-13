package com.example.itest_pro.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itest_pro.MainActivity;
import com.example.itest_pro.Model.Cauhoi;
import com.example.itest_pro.Model.CuocThi;
import com.example.itest_pro.R;
import com.example.itest_pro.ThiActivity;

import java.util.List;


public class AdapterCauHoi extends ArrayAdapter<Cauhoi> {
    private Context context;
    private List<Cauhoi> cauhoiList;
    private AppCompatActivity self;

    public AdapterCauHoi(@NonNull AppCompatActivity self, @NonNull Context context, @NonNull List<Cauhoi> cauhoiList) {
        super(context, R.layout.item_cauhoi, cauhoiList);

        this.context = context;
        this.cauhoiList = cauhoiList;
        this.self = self;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_cauhoi, null);
        }

        Cauhoi c = MainActivity.cauHoiData.getListCauHoi().get(position);

        TextView item_cauhoi_noidung = v.findViewById(R.id.item_cauhoi_noidung);
        TextView item_cauhoi_sodapan = v.findViewById(R.id.item_cauhoi_sodapan);
        TextView item_cauhoi_capdo = v.findViewById(R.id.item_cauhoi_capdo);

        String str_item_cauhoi_sodap_an = c.getSodapandung() > 1 ? "Chọn nhiều đáp án" : "Chọn 1 đáp án";
        String str_item_cauhoi_capdo = "Câu hỏi " + c.getHangid();

        item_cauhoi_noidung.setText(Html.fromHtml(String.valueOf(c.getNoidung())));
        item_cauhoi_sodapan.setText(str_item_cauhoi_sodap_an);
        item_cauhoi_capdo.setText(str_item_cauhoi_capdo);

        AdapterDapAn adapterDapAn = new AdapterDapAn(v.getContext(), c.getCacdapan(), position);

        ListView listView = v.findViewById(R.id.cacdapan);

        listView.setAdapter(adapterDapAn);

        ListAdapter listadp = listView.getAdapter();
        if (listadp != null) {
            int totalHeight = 0;
            for (int i = 0; i < listadp.getCount(); i++) {
                View listItem = listadp.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listadp.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();

        }
        return v;
    }
}
