package com.delaroystudios.alarmreminder.HocTap.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.TuHocMoiNgay;
import com.delaroystudios.alarmreminder.HocTap.view.activity.EditBaiHocActivity;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.AdapterBaiHoc;
import com.delaroystudios.alarmreminder.MainActivity;
import com.delaroystudios.alarmreminder.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MyPC on 19/01/2018.
 */

public class LessonsFragment extends Fragment {
    RecyclerView subjectRecyclerView;
    FloatingActionButton fabAdd;
    AdapterBaiHoc adapterBaiHoc;
    RecyclerView.LayoutManager layoutManager;
    DatabaseAccess databaseAccess;
    SharePreferences sharePreferences;
    public static List<ChuDe2> chuDe2schuahoc = new ArrayList<>();
    public static List<ChuDe2> chuDe2DaHoc = new ArrayList<>();
    public static List<ChuDe2> chuDe2DaHocEdit = new ArrayList<>();
    List<TuHocMoiNgay> tuHocMoiNgays;
    private long mLastClickTime = 0;
    StringBuffer noidung  = new StringBuffer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons,container,false);
        subjectRecyclerView = (RecyclerView) view.findViewById(R.id.subjectRecyclerView);
        fabAdd = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        databaseAccess = new DatabaseAccess(getContext());
        databaseAccess.open();
        sharePreferences = new SharePreferences(getContext());
        sharePreferences.init(getContext());
        chuDe2schuahoc = databaseAccess.GetChuDe2ChuaHoc("0");
        sapxep(chuDe2schuahoc);
        if(sharePreferences.getNGAY_MOI()==0){
            int dem = sharePreferences.getHOC_NGAY();
            for (int i = 0 ; i < dem ; i++){
                if(i == dem-1){
                    noidung.append(chuDe2schuahoc.get(i).getTenChuDe()+" ");
                }else {
                    noidung.append(chuDe2schuahoc.get(i).getTenChuDe()+", ");
                }
                chuDe2DaHoc.add(chuDe2schuahoc.get(i));
                chuDe2DaHocEdit.addAll(chuDe2DaHoc);
            }
            databaseAccess.themBaiHoc(sharePreferences.getTHEMBAIhOC()+"",noidung+"");
            int dem1 = chuDe2DaHoc.size();
            if(sharePreferences.getHOC_NGAY()==5){
                for (int i = 0 ; i < dem1 ; i++){
                    if(i==0){
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),sharePreferences.getTHEMBAIhOC()+"",sharePreferences.getTHEMBAIhOC()+"");
                    }else {
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),"0",sharePreferences.getTHEMBAIhOC()+"");
                    }
                }
            }else if(sharePreferences.getHOC_NGAY()==10){
                for (int i = 0 ; i < dem1 ; i++){
                    if(i==0||i==1){
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),sharePreferences.getTHEMBAIhOC()+"",sharePreferences.getTHEMBAIhOC()+"");
                    }else {
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),"0",sharePreferences.getTHEMBAIhOC()+"");
                    }
                }
            }else if(sharePreferences.getHOC_NGAY()==15){
                for (int i = 0 ; i < dem1 ; i++){
                    if(i==0||i==1||i==2){
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),sharePreferences.getTHEMBAIhOC()+"",sharePreferences.getTHEMBAIhOC()+"");
                    }else {
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),"0",sharePreferences.getTHEMBAIhOC()+"");
                    }
                }
            }else if(sharePreferences.getHOC_NGAY()==20){
                for (int i = 0 ; i < dem1 ; i++){
                    if(i==0||i==1||i==2||i==3){
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),sharePreferences.getTHEMBAIhOC()+"",sharePreferences.getTHEMBAIhOC()+"");
                    }else {
                        databaseAccess.UpdateChuDe2(chuDe2DaHoc.get(i).getMachuDe(),chuDe2DaHoc.get(i).getTenChuDe(),chuDe2DaHoc.get(i).getMaTruyVan(),"0",sharePreferences.getTHEMBAIhOC()+"");
                    }
                }
            }
            sharePreferences.putNGAY_MOI(1);
            sharePreferences.putTHEMBAIhOC(sharePreferences.getTHEMBAIhOC()+1);

        }else {
            chuDe2DaHoc = databaseAccess.GetChuDe2DangHoc(sharePreferences.getTHEMBAIhOC()-1+"");
            chuDe2DaHocEdit.addAll(chuDe2DaHoc);
        }
        tuHocMoiNgays = databaseAccess.GetBaiHocTrongNgay();
        adapterBaiHoc = new AdapterBaiHoc(getContext(),tuHocMoiNgays);
        layoutManager = new LinearLayoutManager(getActivity());
        subjectRecyclerView.setLayoutManager(layoutManager);
        subjectRecyclerView.setAdapter(adapterBaiHoc);
        adapterBaiHoc.notifyDataSetChanged();

        ClickEdit();
        return view;
    }
        public void ClickEdit(){
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent = new Intent(getContext(), EditBaiHocActivity.class);
                startActivity(intent);
            }

        });
}

public void sapxep(List<ChuDe2> myList){
    Collections.sort(myList, new Comparator<ChuDe2>(){
        @Override
        public int compare(ChuDe2 o1, ChuDe2 o2) {
            return Integer.valueOf(o1.getMaTruyVan()).compareTo(Integer.valueOf(o2.getMaTruyVan()));
        }
    });
}

    @Override
    public void onResume() {
        super.onResume();
        if(EditBaiHocActivity.kiemtra){
            subjectRecyclerView.removeAllViews();
            StringBuffer noidung1 = new StringBuffer();
            int dem = sharePreferences.getHOC_NGAY();
            for (int i = 0 ; i < dem ; i++){
                if(i == dem-1){
                    noidung1.append(chuDe2DaHocEdit.get(i).getTenChuDe()+" ");
                }else {
                    noidung1.append(chuDe2DaHocEdit.get(i).getTenChuDe()+", ");
                }
            }
            databaseAccess.SuaBaiHoc((sharePreferences.getTHEMBAIhOC()-1)+"",(sharePreferences.getTHEMBAIhOC()-1)+"",noidung1+"");

            List<TuHocMoiNgay> tuHocMoiNgay = databaseAccess.GetBaiHocTrongNgay();
            adapterBaiHoc = new AdapterBaiHoc(getContext(),tuHocMoiNgay);
            layoutManager = new LinearLayoutManager(getActivity());
            subjectRecyclerView.setLayoutManager(layoutManager);
            subjectRecyclerView.setAdapter(adapterBaiHoc);
            adapterBaiHoc.notifyDataSetChanged();
        }
    }
}
