package com.delaroystudios.alarmreminder.HocTap.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.Chude1;
import com.delaroystudios.alarmreminder.HocTap.view.activity.NoiDungChuDe1;
import com.delaroystudios.alarmreminder.R;

import java.util.List;


/**
 * Created by bluevy on 12/01/2018.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{
    Context context;
    List<Chude1> items;
    DatabaseAccess databaseAccess;
    List<ChuDe2> chuDe2s;

    public SubjectAdapter(Context context, List<Chude1> items) {
        this.context = context;
        this.items = items;
        databaseAccess = new DatabaseAccess(context);
        databaseAccess.open();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectCardTextView;
        RecyclerView wordRecyclerView;
        ImageView imvDeletecd1;
        public ViewHolder(View itemView) {
            super(itemView);
            wordRecyclerView = (RecyclerView) itemView.findViewById(R.id.wordRecyclerView);
            subjectCardTextView = (TextView) itemView.findViewById(R.id.subjectCardTextView);
            imvDeletecd1 = (ImageView) itemView.findViewById(R.id.imvDeletecd1);
        }
    }
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.subject_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.ViewHolder holder, final int position) {
            final Chude1 chude1 = items.get(position);

            chuDe2s = databaseAccess.GetChuDe2(chude1.getMachude());
            holder.subjectCardTextView.setText(chude1.getTenChuDe());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
            WordAdapter wordAdapter = new WordAdapter(context,chuDe2s,chude1.getTenChuDe());
            holder.wordRecyclerView.setLayoutManager(layoutManager);
            holder.wordRecyclerView.setAdapter(wordAdapter);
            wordAdapter.notifyDataSetChanged();

            holder.imvDeletecd1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAccess.XoaChuDe1(chude1.getMachude());
                    databaseAccess.XoahetChuDe2(chude1.getMachude());
                    items.remove(position);
                    notifyDataSetChanged();
                }
            });

            holder.subjectCardTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NoiDungChuDe1.class);
                    intent.putExtra("machude1",chude1.getMachude());
                    intent.putExtra("tenchude",chude1.getTenChuDe());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
