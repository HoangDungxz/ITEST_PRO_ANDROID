package com.example.itest_pro.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.itest_pro.MainActivity;
import com.example.itest_pro.Model.Cauhoi;
import com.example.itest_pro.Model.DapAn;
import com.example.itest_pro.R;

import java.util.List;


public class AdapterDapAn extends ArrayAdapter<DapAn> {
    private Context context;
    private List<DapAn> dapAnList;
    private Integer cauhoipos;

    public AdapterDapAn(@NonNull Context context, @NonNull List<DapAn> dapAnList, @NonNull Integer cauhoipos) {
        super(context, R.layout.item_dapan, dapAnList);

        this.context = context;
        this.dapAnList = dapAnList;
        this.cauhoipos = cauhoipos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_dapan, null);
        }

        DapAn d = MainActivity.cauHoiData.getListCauHoi().get(this.cauhoipos).getCacdapan().get(position);

        TextView item_dapan_noidung = v.findViewById(R.id.item_dapan_noidung);
        CheckBox item_dapan_check = v.findViewById(R.id.item_dapan_check);

        item_dapan_noidung.setText(Html.fromHtml(String.valueOf(d.getNoidung())));
        item_dapan_check.setText(String.valueOf(d.getId()));
        Integer cauhoipos2 = this.cauhoipos;


        item_dapan_check.setChecked(d.isChecked());


        //Bắt sự kiện thay đổi trạng thái
        item_dapan_check.setTag(position);
        item_dapan_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPos = (int) v.getTag();
                boolean isChecked = false;
                if (dapAnList.get(currentPos).isChecked() == false) {
                    isChecked = true;
                }
               
                MainActivity.cauHoiData.getListCauHoi().get(cauhoipos2).getCacdapan().get(currentPos).setChecked(isChecked);

                notifyDataSetChanged();
            }
        });

        return v;
    }
}
