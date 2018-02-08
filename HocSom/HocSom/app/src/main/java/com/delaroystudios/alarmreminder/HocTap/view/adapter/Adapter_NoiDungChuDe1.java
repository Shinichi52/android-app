package com.delaroystudios.alarmreminder.HocTap.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.view.activity.EditChuDe2;
import com.delaroystudios.alarmreminder.R;

import java.util.List;


/**
 * Created by MyPC on 17/01/2018.
 */

public class Adapter_NoiDungChuDe1 extends RecyclerView.Adapter<Adapter_NoiDungChuDe1.ViewHolder>{
    Context context;
    List<ChuDe2> items;
    String machude;
    SharePreferences sharePreferences;
    public Adapter_NoiDungChuDe1(Context context, List<ChuDe2> items, String machude) {
        this.context = context;
        this.items = items;
        this.machude = machude;
        sharePreferences = new SharePreferences(context);
        sharePreferences.init(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordCardTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            wordCardTextView = (TextView) itemView.findViewById(R.id.wordCardTextView);
        }
    }


    @Override
    public Adapter_NoiDungChuDe1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_adapterchude1,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ChuDe2 chuDe2 = items.get(position);

        if(sharePreferences.getMAU_CHU()==0){
            holder.wordCardTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.wordCardTextView.setTextColor(context.getResources().getColor(R.color.black));
        }
        String tenchude = chuDe2.getTenChuDe();
        String tenchude1;
        if(sharePreferences.getCHUHOA_CHUTHUONG()==0){
            tenchude1 = tenchude.toLowerCase();
        }else {
            tenchude1 = tenchude.toUpperCase();
        }

        holder.wordCardTextView.setText(tenchude1);
        holder.wordCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditChuDe2.class);
                intent.putExtra("MaChuDe",chuDe2.getMachuDe());
                intent.putExtra("TenChuDe",chuDe2.getTenChuDe());
                intent.putExtra("MaTruyVan",chuDe2.getMaTruyVan());
                intent.putExtra("machude",machude);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}

