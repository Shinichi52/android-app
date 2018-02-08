package com.delaroystudios.alarmreminder.HocTap.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.MainActivity;
import com.delaroystudios.alarmreminder.R;


public class SettingsFragment extends Fragment {
    Switch swChuHoa;
    Button btnMauChu;
    TextView txtSotuHocTrongNgay,txtTuHocMoi;
    SharePreferences sharePreferences;
    DatabaseAccess databaseAccess;
    LinearLayout lnNhacNho;
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        swChuHoa = (Switch) view.findViewById(R.id.swChuHoa);
        btnMauChu = (Button) view.findViewById(R.id.btnMauChu);
        txtSotuHocTrongNgay = (TextView) view.findViewById(R.id.txtSotuHocTrongNgay);
        txtTuHocMoi = (TextView) view.findViewById(R.id.txtTuHocMoi);
        lnNhacNho = (LinearLayout) view.findViewById(R.id.lnNhacNho);
        databaseAccess = new DatabaseAccess(getContext());
        databaseAccess.open();
        sharePreferences = new SharePreferences(getContext());
        sharePreferences.init(getContext());
        if(sharePreferences.getMAU_CHU()==0){
            btnMauChu.setBackground(getActivity().getDrawable(R.drawable.custom_btn_color));
        }else {
            btnMauChu.setBackground(getActivity().getDrawable(R.drawable.custom_btncolor_den));
        }
        if(sharePreferences.getCHUHOA_CHUTHUONG()==0){
            swChuHoa.setChecked(false);
        }else {
            swChuHoa.setChecked(true);
        }
        if(sharePreferences.getHOC_NGAY()==5) {
            txtSotuHocTrongNgay.setText("5");
            txtTuHocMoi.setText("1");
        }
        if(sharePreferences.getHOC_NGAY()==10) {
            txtSotuHocTrongNgay.setText("10");
            txtTuHocMoi.setText("2");
        }
        if(sharePreferences.getHOC_NGAY()==15) {
            txtSotuHocTrongNgay.setText("15");
            txtTuHocMoi.setText("3");
        }
        if(sharePreferences.getHOC_NGAY()==20) {
            txtSotuHocTrongNgay.setText("20");
            txtTuHocMoi.setText("4");
        }
        btnMauChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharePreferences.getMAU_CHU()==0){
                    btnMauChu.setBackground(getActivity().getDrawable(R.drawable.custom_btncolor_den));
                    sharePreferences.putMAU_CHU(1);
                    Toast.makeText(getActivity(), "Chữ đen", Toast.LENGTH_SHORT).show();
                    onResume();
                }else {
                    btnMauChu.setBackground(getActivity().getDrawable(R.drawable.custom_btn_color));
                    sharePreferences.putMAU_CHU(0);
                    Toast.makeText(getActivity(), "Chữ đỏ", Toast.LENGTH_SHORT).show();
                    onResume();
                }
            }
        });
        swChuHoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharePreferences.putCHUHOA_CHUTHUONG(1);
                    Toast.makeText(getActivity(), "Chữ hoa", Toast.LENGTH_SHORT).show();
                    onResume();
                }else {
                    sharePreferences.putCHUHOA_CHUTHUONG(0);
                    Toast.makeText(getActivity(), "Chữ thường", Toast.LENGTH_SHORT).show();
                    onResume();
                }

            }
        });
        lnNhacNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        txtSotuHocTrongNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Số từ học/ ngày");
                String[] types = {"5 từ/ ngày", "10 từ/ ngày","15 từ/ ngày","20 từ/ ngày"};
                b.setItems(types, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        switch(which){
                            case 0:
                                txtSotuHocTrongNgay.setText("5");
                                txtTuHocMoi.setText("1");
                                sharePreferences.putHOC_NGAY(5);
                                dialog.cancel();
                                break;
                            case 1:
                                txtSotuHocTrongNgay.setText("10");
                                txtTuHocMoi.setText("2");
                                sharePreferences.putHOC_NGAY(10);
                                dialog.cancel();
                                break;
                            case 2:
                                txtSotuHocTrongNgay.setText("15");
                                txtTuHocMoi.setText("3");
                                sharePreferences.putHOC_NGAY(15);
                                dialog.cancel();
                                break;
                            case 3:
                                txtSotuHocTrongNgay.setText("20");
                                txtTuHocMoi.setText("4");
                                sharePreferences.putHOC_NGAY(20);
                                dialog.cancel();
                                break;
                        }
                    }

                });

                b.show();
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
    }
}
