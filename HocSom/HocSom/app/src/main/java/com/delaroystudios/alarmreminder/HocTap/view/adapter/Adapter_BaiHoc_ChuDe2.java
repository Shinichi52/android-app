package com.delaroystudios.alarmreminder.HocTap.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.view.activity.EditBaiHocActivity;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.LessonsFragment;
import com.delaroystudios.alarmreminder.R;

import java.util.List;

/**
 * Created by MyPC on 21/01/2018.
 */

public class Adapter_BaiHoc_ChuDe2 extends RecyclerView.Adapter<Adapter_BaiHoc_ChuDe2.Viewhodel> {
    Context context;
    List<ChuDe2> items;
    SharePreferences sharePreferences;
    TextView txtsotu;
    boolean kiemtra = false;
    DatabaseAccess databaseAccess;

    public Adapter_BaiHoc_ChuDe2(Context context, List<ChuDe2> items,TextView txtsotu) {
        this.context = context;
        this.items = items;
        this.txtsotu = txtsotu;
        databaseAccess = new DatabaseAccess(context);
        databaseAccess.open();
        sharePreferences = new SharePreferences(context);
        sharePreferences.init(context);
    }

    public class Viewhodel extends RecyclerView.ViewHolder {
        TextView wordCardTextView;
        ImageView imvCheck;
        public Viewhodel(View itemView) {
            super(itemView);
            wordCardTextView = (TextView) itemView.findViewById(R.id.wordCardTextView);
            imvCheck = (ImageView) itemView.findViewById(R.id.imvCheck);

        }
    }
    @Override
    public Viewhodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_baihoc_edichude2,parent,false);
        Viewhodel viewhodel = new Viewhodel(view);
        return viewhodel;
    }

    @Override
    public void onBindViewHolder(final Viewhodel holder, int position) {
        final ChuDe2 chuDe2 = items.get(position);
        String ten = chuDe2.getTenChuDe();
        String tenchude1;
        if(sharePreferences.getCHUHOA_CHUTHUONG()==0){
            tenchude1 = ten.toLowerCase();
        }else {
            tenchude1 = ten.toUpperCase();
        }
        if(sharePreferences.getMAU_CHU()==0){
            holder.wordCardTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.wordCardTextView.setTextColor(context.getResources().getColor(R.color.black));
        }
        if (chuDe2.getTuhomnay().equals(sharePreferences.getTHEMBAIhOC()-1+"")){
            holder.imvCheck.setVisibility(View.VISIBLE);
            kiemtra = true;
        }else {
            holder.imvCheck.setVisibility(View.GONE);
            kiemtra = false;
        }
        holder.wordCardTextView.setText(tenchude1);

        holder.wordCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.imvCheck.getVisibility()==View.VISIBLE){
                    EditBaiHocActivity.chuDe2sBoCheck.add(chuDe2);
                    for (int i = 0 ; i < LessonsFragment.chuDe2DaHoc.size();i++){
                        if(chuDe2.getMaTruyVan().equals(LessonsFragment.chuDe2DaHoc.get(i).getMaTruyVan())){
                            LessonsFragment.chuDe2DaHoc.remove(i);
                            holder.imvCheck.setVisibility(View.GONE);
                            EditBaiHocActivity.demsotu = EditBaiHocActivity.demsotu-1;
                            txtsotu.setText(EditBaiHocActivity.demsotu+"/"+sharePreferences.getHOC_NGAY());
                        }
                    }
                }else {
                    if(EditBaiHocActivity.demsotu==sharePreferences.getHOC_NGAY()){
                        Toast.makeText(context, "Số từ không được vượt quá "+EditBaiHocActivity.demsotu+"/"+sharePreferences.getHOC_NGAY(), Toast.LENGTH_SHORT).show();
                    }else {
                        holder.imvCheck.setVisibility(View.VISIBLE);
                        EditBaiHocActivity.demsotu = EditBaiHocActivity.demsotu+1;
                        txtsotu.setText(EditBaiHocActivity.demsotu+"/"+sharePreferences.getHOC_NGAY());
                        ChuDe2 chuDe21 = new ChuDe2();
                        chuDe21.setMachuDe(chuDe2.getMachuDe());
                        chuDe21.setTenChuDe(chuDe2.getTenChuDe());
                        chuDe21.setMaTruyVan(chuDe2.getMaTruyVan());
                        chuDe21.setTuDaHoc(chuDe2.getTuDaHoc());
                        chuDe21.setTuhomnay(chuDe2.getTuhomnay());
                        LessonsFragment.chuDe2DaHoc.add(chuDe21);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
