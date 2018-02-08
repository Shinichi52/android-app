package com.delaroystudios.alarmreminder.HocTap.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.Chude1;
import com.delaroystudios.alarmreminder.HocTap.view.activity.AddChuDe2;
import com.delaroystudios.alarmreminder.HocTap.view.activity.EditChuDe2;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.SubjectAdapter;
import com.delaroystudios.alarmreminder.R;

import java.util.List;



/**
 * Created by bluevy on 11/01/2018.
 */

public class SubjectsFragment extends Fragment {
        protected RecyclerView mRecyclerView;
        protected SubjectAdapter mAdapter;
        protected RecyclerView.LayoutManager mLayoutManager;
        public static List<Chude1> chude1s;
        DatabaseAccess databaseAccess;
        FloatingActionButton fabAdd;
        private long mLastClickTime = 0;
        public static SubjectsFragment newInstance(String param1, String param2) {
            SubjectsFragment fragment = new SubjectsFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            databaseAccess = new DatabaseAccess(getContext());
            databaseAccess.open();
            chude1s = databaseAccess.GetChuDe1();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_subjects, container, false);
            fabAdd = (FloatingActionButton) view.findViewById(R.id.fabAdd);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.subjectRecyclerView);
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new SubjectAdapter(getContext(),chude1s);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View dialogView= inflater.inflate(R.layout.custom_alefdialog, null);
                    dialogBuilder.setView(dialogView);
                    dialogBuilder.setCancelable(false);

                    final EditText edtnoidung = (EditText) dialogView.findViewById(R.id.edtnoidung);
                    Button btnback = (Button) dialogView.findViewById(R.id.btnback);
                    Button btndongy = (Button) dialogView.findViewById(R.id.btndongy);
                    final AlertDialog ad = dialogBuilder.show();
                    btnback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ad.cancel();
                        }
                    });
                    btndongy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String kiemtra = edtnoidung.getText().toString().trim();
                            if(kiemtra.length()==0){
                                Toast.makeText(getActivity(), "Nội dung không được để trống !!!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "Thêm chủ đề thành công", Toast.LENGTH_SHORT).show();
                                databaseAccess.ThemChuDe1(String.valueOf(chude1s.size()+1),kiemtra);
                                chude1s = databaseAccess.GetChuDe1();
                                mRecyclerView.removeAllViews();
                                mAdapter = new SubjectAdapter(getContext(),chude1s);
                                mRecyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                                ad.cancel();
                            }
                        }
                    });
                }
            });
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        if(AddChuDe2.kiemtrathem){
            databaseAccess.open();
            chude1s = databaseAccess.GetChuDe1();
            mRecyclerView.removeAllViews();
            mAdapter = new SubjectAdapter(getContext(),chude1s);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
        if(EditChuDe2.kiemtraxoa){
            databaseAccess.open();
            chude1s = databaseAccess.GetChuDe1();
            mRecyclerView.removeAllViews();
            mAdapter = new SubjectAdapter(getContext(),chude1s);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }
}
