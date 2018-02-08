package com.delaroystudios.alarmreminder.HocTap.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.Adapter_NoiDungChuDe1;
import com.delaroystudios.alarmreminder.R;

import java.util.ArrayList;
import java.util.List;


public class NoiDungChuDe1 extends AppCompatActivity {
    TextView txtChuDe1;
    ImageView imvBack;
    RecyclerView recyclerview_danhsachchude2;
    String machude1;
    String tenchude;
    DatabaseAccess databaseAccess;
    List<ChuDe2> items = new ArrayList<>();
    Adapter_NoiDungChuDe1 adapter_noiDungChuDe1;
    FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung_chu_de1);
        recyclerview_danhsachchude2 = (RecyclerView) findViewById(R.id.recyclerview_danhsachchude2);
        txtChuDe1 = (TextView) findViewById(R.id.txtChuDe1);
        imvBack = (ImageView) findViewById(R.id.imvBack);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        databaseAccess = new DatabaseAccess(NoiDungChuDe1.this);
        databaseAccess.open();
        machude1 = getIntent().getStringExtra("machude1");
        tenchude = getIntent().getStringExtra("tenchude");
        items = databaseAccess.GetChuDe2(machude1);

        txtChuDe1.setText(tenchude);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter_noiDungChuDe1 = new Adapter_NoiDungChuDe1(this,items,tenchude);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
        recyclerview_danhsachchude2.setAdapter(adapter_noiDungChuDe1);
        recyclerview_danhsachchude2.setLayoutManager(layoutManager);
        adapter_noiDungChuDe1.notifyDataSetChanged();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoiDungChuDe1.this,AddChuDe2.class);
                intent.putExtra("tenchude",tenchude);
                intent.putExtra("machude1",machude1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AddChuDe2.kiemtrathem){
            databaseAccess.open();
            items = databaseAccess.GetChuDe2(machude1);
            adapter_noiDungChuDe1 = new Adapter_NoiDungChuDe1(this,items,tenchude);
            recyclerview_danhsachchude2.setAdapter(adapter_noiDungChuDe1);
            adapter_noiDungChuDe1.notifyDataSetChanged();
        }
        if(EditChuDe2.kiemtraxoa){
            databaseAccess.open();
            items = databaseAccess.GetChuDe2(machude1);
            adapter_noiDungChuDe1 = new Adapter_NoiDungChuDe1(this,items,tenchude);
            recyclerview_danhsachchude2.setAdapter(adapter_noiDungChuDe1);
            adapter_noiDungChuDe1.notifyDataSetChanged();
        }
    }
}
