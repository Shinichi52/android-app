package com.delaroystudios.alarmreminder.HocTap.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.Chude1;
import com.delaroystudios.alarmreminder.R;

import java.util.List;

/**
 * Created by MyPC on 21/01/2018.
 */

public class Custom_adapter_editbaihoc extends RecyclerView.Adapter<Custom_adapter_editbaihoc.Viewhodel> {
    List<Chude1> items;
    Context context;
    DatabaseAccess databaseAccess;
    SharePreferences sharePreferences;
    TextView textView;
    public Custom_adapter_editbaihoc(List<Chude1> items, Context context,TextView textView) {
        this.items = items;
        this.context = context;
        this.textView = textView;
        databaseAccess = new DatabaseAccess(context);
        databaseAccess.open();
        sharePreferences = new SharePreferences(context);
        sharePreferences.init(context);
    }

    public class Viewhodel extends RecyclerView.ViewHolder {
        TextView tv_edit_chude1;
        RecyclerView recyc_edit_chude2;
        public Viewhodel(View itemView) {
            super(itemView);
            tv_edit_chude1 = (TextView) itemView.findViewById(R.id.tv_edit_chude1);
            recyc_edit_chude2 = (RecyclerView) itemView.findViewById(R.id.recyc_edit_chude2);
        }
    }

    @Override
    public Viewhodel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_adapter_editbaihoc,parent,false);
        Viewhodel viewhodel = new Viewhodel(view);
        return viewhodel;
    }

    @Override
    public void onBindViewHolder(Viewhodel holder, int position) {
        Chude1 chude1 = items.get(position);
        holder.tv_edit_chude1.setText(chude1.getTenChuDe());
        List<ChuDe2> chuDe2s = databaseAccess.GetChuDe2(chude1.getMachude());

        //xóa từ đã học ngày hôm trước
        for (int i = 0 ; i < chuDe2s.size();i++){
            if(Integer.parseInt(chuDe2s.get(i).getTuDaHoc())!=0
                    && Integer.parseInt(chuDe2s.get(i).getTuDaHoc())!=(sharePreferences.getTHEMBAIhOC()-1)&&sharePreferences.getTHEMBAIhOC()-1!=1){
                chuDe2s.remove(i);
            }
        }
        Adapter_BaiHoc_ChuDe2 adapter_baiHoc_chuDe2 = new Adapter_BaiHoc_ChuDe2(context,chuDe2s,textView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false);
        holder.recyc_edit_chude2.setLayoutManager(layoutManager);
        holder.recyc_edit_chude2.setAdapter(adapter_baiHoc_chuDe2);
        adapter_baiHoc_chuDe2.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
