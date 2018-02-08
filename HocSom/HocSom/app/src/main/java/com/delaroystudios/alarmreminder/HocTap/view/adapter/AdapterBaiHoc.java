package com.delaroystudios.alarmreminder.HocTap.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.BaiHoc;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.TuHocMoiNgay;
import com.delaroystudios.alarmreminder.R;

import java.util.List;

/**
 * Created by MyPC on 19/01/2018.
 */

public class AdapterBaiHoc extends RecyclerView.Adapter<AdapterBaiHoc.Viewhodel>{
        Context context;
        List<TuHocMoiNgay> items;
        SharePreferences sharePreferences;

    public AdapterBaiHoc(Context context, List<TuHocMoiNgay> items) {
        this.context = context;
        this.items = items;
        sharePreferences = new SharePreferences(context);
        sharePreferences.init(context);
    }

    public class Viewhodel extends RecyclerView.ViewHolder {
        TextView txtNgay,tvNoiDung;
            public Viewhodel(View itemView) {
                super(itemView);
                txtNgay = (TextView) itemView.findViewById(R.id.txtNgay);
                tvNoiDung = (TextView) itemView.findViewById(R.id.tvNoiDung);
            }
        }

    @Override
    public Viewhodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_adapter_baihoc,parent,false);
        Viewhodel viewhodel = new Viewhodel(view);
        return viewhodel;
    }

    @Override
    public void onBindViewHolder(Viewhodel holder, int position) {
        TuHocMoiNgay baiHoc = items.get(position);
        String noidung = baiHoc.getNoidung();
        String noidung1;
        if(sharePreferences.getCHUHOA_CHUTHUONG()==0){
            noidung1 = noidung.toLowerCase();
        }else {
            noidung1 = noidung.toUpperCase();
        }
        if(sharePreferences.getMAU_CHU()==0){
            holder.tvNoiDung.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.txtNgay.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.tvNoiDung.setTextColor(context.getResources().getColor(R.color.black));
            holder.txtNgay.setTextColor(context.getResources().getColor(R.color.black));
        }
        holder.txtNgay.setText(baiHoc.getNgay());
        holder.tvNoiDung.setText(noidung1);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
