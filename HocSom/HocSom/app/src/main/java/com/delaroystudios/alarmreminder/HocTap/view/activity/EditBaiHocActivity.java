package com.delaroystudios.alarmreminder.HocTap.view.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.Chude1;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.Custom_adapter_editbaihoc;
import com.delaroystudios.alarmreminder.HocTap.view.adapter.SubjectAdapter;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.LessonsFragment;
import com.delaroystudios.alarmreminder.R;

import java.util.ArrayList;
import java.util.List;

public class EditBaiHocActivity extends AppCompatActivity {
    RecyclerView recyclerview_editbaihoc;
    public List<Chude1> chude1s;
    DatabaseAccess databaseAccess;
    ImageView imvBack,imvSave;
    Custom_adapter_editbaihoc custom_adapter_editbaihoc;
    SharePreferences sharePreferences;
    public static List<ChuDe2> chuDe2sBoCheck  = new ArrayList<>();
    TextView txtSotu;
    public static int demsotu;
    public static boolean kiemtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bai_hoc);
        recyclerview_editbaihoc = (RecyclerView) findViewById(R.id.recyclerview_editbaihoc);
        imvBack = (ImageView) findViewById(R.id.imvBack);
        imvSave = (ImageView) findViewById(R.id.imvSave);
        txtSotu = (TextView) findViewById(R.id.txtSotu);
        databaseAccess = new DatabaseAccess(this);
        databaseAccess.open();
        sharePreferences = new SharePreferences(this);
        sharePreferences.init(this);
        chude1s = databaseAccess.GetChuDe1();
        custom_adapter_editbaihoc = new Custom_adapter_editbaihoc(chude1s,this,txtSotu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview_editbaihoc.setAdapter(custom_adapter_editbaihoc);
        recyclerview_editbaihoc.setLayoutManager(layoutManager);
        custom_adapter_editbaihoc.notifyDataSetChanged();

        XuLiSoTu();
        XuLiBack();
        Save();
    }

    public void Save(){
        imvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(demsotu!=sharePreferences.getHOC_NGAY()){
                    Toast.makeText(EditBaiHocActivity.this, "Số từ phải đủ "+sharePreferences.getHOC_NGAY()+ "/"+sharePreferences.getHOC_NGAY(), Toast.LENGTH_SHORT).show();
                }else {
                    //Updata bỏ vị trí chọn
                    BoviTriChon();

                    //thêm vị trí số 1 là từ đã học
                    if(Integer.parseInt(LessonsFragment.chuDe2DaHoc.get(0).getTuDaHoc())!=sharePreferences.getTHEMBAIhOC()-1){
                        LessonsFragment.chuDe2DaHoc.get(0).setTuDaHoc(sharePreferences.getTHEMBAIhOC()-1+"");
                        databaseAccess.UpdateChuDe2(LessonsFragment.chuDe2DaHoc.get(0).getMachuDe(),
                                LessonsFragment.chuDe2DaHoc.get(0).getTenChuDe(),
                                LessonsFragment.chuDe2DaHoc.get(0).getMaTruyVan(),
                                sharePreferences.getTHEMBAIhOC()-1+"",
                                sharePreferences.getTHEMBAIhOC()-1+"");
                    }
                    for (int i = 0 ; i < sharePreferences.getHOC_NGAY();i++){
                        if(i!=0){
                            databaseAccess.UpdateChuDe2(LessonsFragment.chuDe2DaHoc.get(i).getMachuDe(),LessonsFragment.chuDe2DaHoc.get(i).getTenChuDe()
                                    ,LessonsFragment.chuDe2DaHoc.get(i).getMaTruyVan(),"0",(sharePreferences.getTHEMBAIhOC()-1)+"");
                        }
                    }
                    LessonsFragment.chuDe2DaHocEdit.clear();
                    LessonsFragment.chuDe2DaHocEdit.addAll(LessonsFragment.chuDe2DaHoc);
                    kiemtra = true;
                    Toast.makeText(EditBaiHocActivity.this, "Lưu thay đổi thành công", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            }
        });
    }
    public void XuLiSoTu(){
        demsotu = sharePreferences.getHOC_NGAY();
        if(sharePreferences.getHOC_NGAY()==5){
            txtSotu.setText("5/5");
        } if(sharePreferences.getHOC_NGAY()==10){
            txtSotu.setText("10/10");
        } if(sharePreferences.getHOC_NGAY()==15){
            txtSotu.setText("15/15");
        } if(sharePreferences.getHOC_NGAY()==20){
            txtSotu.setText("20/20");
        }
    }
    public void XuLiBack(){
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(demsotu!=sharePreferences.getHOC_NGAY()){
                    Showdialog();
                }else {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        chuDe2sBoCheck.clear();
        chuDe2sBoCheck = new ArrayList<>();
    }

    private void Showdialog(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        EditBaiHocActivity.demsotu = sharePreferences.getHOC_NGAY();
                        kiemtra = true;
                        onBackPressed();
                        dialog.dismiss();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                       dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(EditBaiHocActivity.this);
        builder.setMessage("Quay lại và không lưu thay đổi !!!").setTitle("Chưa chọn đủ số từ "+demsotu+"/"+sharePreferences.getHOC_NGAY()).setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private void BoviTriChon(){
        if(chuDe2sBoCheck.size()!=0){
            for (int i = 0 ; i < chuDe2sBoCheck.size();i++){
                databaseAccess.UpdateChuDe2(chuDe2sBoCheck.get(i).getMachuDe(),chuDe2sBoCheck.get(i).getTenChuDe()
                        ,chuDe2sBoCheck.get(i).getMaTruyVan(),"0","0");

            }
        }

    }

}
