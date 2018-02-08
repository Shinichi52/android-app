package com.delaroystudios.alarmreminder.HocTap.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
//import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.TuHocMoiNgay;
import com.delaroystudios.alarmreminder.HocTap.view.activity.EditBaiHocActivity;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.AdapterBaiHoc;
import com.delaroystudios.alarmreminder.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HistoryFragment extends Fragment {
    RecyclerView subjectRecyclerView;
    // FloatingActionButton fabAdd;
    AdapterBaiHoc adapterBaiHoc;
    RecyclerView.LayoutManager layoutManager;
    DatabaseAccess databaseAccess;
    SharePreferences sharePreferences;
    public static List<ChuDe2> chuDe2schuahoc = new ArrayList<>();
    public static List<ChuDe2> chuDe2DaHoc = new ArrayList<>();
    public static List<ChuDe2> chuDe2DaHocEdit = new ArrayList<>();
    List<TuHocMoiNgay> tuHocMoiNgays;
   // private long mLastClickTime = 0;
    StringBuffer noidung  = new StringBuffer();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);
        subjectRecyclerView = (RecyclerView) view.findViewById(R.id.subjectRecyclerView);
        //fabAdd = (FloatingActionButton) view.findViewById(R.id.fabAdd);
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

        //ClickEdit();
        return view;
    }
    /*
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
    */

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



/*
public class HistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
/*
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
*/