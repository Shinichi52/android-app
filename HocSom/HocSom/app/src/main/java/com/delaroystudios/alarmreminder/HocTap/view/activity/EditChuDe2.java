package com.delaroystudios.alarmreminder.HocTap.view.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.R;

import java.util.Locale;


public class EditChuDe2 extends AppCompatActivity {
    String tenchude,machude,matruyvan,ten;
    TextView txtChuDe1,edtnoidung;
    LinearLayout lnnghe,lnxoa;
    TextToSpeech textToSpeech;
    DatabaseAccess databaseAccess;
    ImageView imvBack;
    SharePreferences sharePreferences;
    public static boolean kiemtraxoa = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chu_de2);
        tenchude = getIntent().getStringExtra("TenChuDe");
        machude = getIntent().getStringExtra("MaChuDe");
        matruyvan = getIntent().getStringExtra("MaTruyVan");
        ten = getIntent().getStringExtra("machude");

        databaseAccess = new DatabaseAccess(this);
        databaseAccess.open();
        sharePreferences = new SharePreferences(EditChuDe2.this);
        sharePreferences.init(this);
        imvBack = (ImageView) findViewById(R.id.imvBack);
        txtChuDe1 = (TextView) findViewById(R.id.txtChuDe1);
        edtnoidung = (TextView) findViewById(R.id.edtnoidung);
        lnnghe = (LinearLayout) findViewById(R.id.lnnghe);
        lnxoa = (LinearLayout) findViewById(R.id.lnxoa);


        if(sharePreferences.getMAU_CHU()==0){
            edtnoidung.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            edtnoidung.setTextColor(getResources().getColor(R.color.black));
        }
        String tenchude1;
        if(sharePreferences.getCHUHOA_CHUTHUONG()==0){
            tenchude1 = tenchude.toLowerCase();
        }else {
            tenchude1 = tenchude.toUpperCase();
        }
        edtnoidung.setText(tenchude1);
        lnnghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(EditChuDe2.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        textToSpeech.setLanguage(Locale.forLanguageTag("vi"));
                        textToSpeech.speak(edtnoidung.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
                    }
                });
            }
        });

        lnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean kiemtra = databaseAccess.XoaChuDe2(matruyvan);
            if(kiemtra){
                Toast.makeText(EditChuDe2.this, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                kiemtraxoa = true;
                finish();
            }else {
                Toast.makeText(EditChuDe2.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
            }
            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        txtChuDe1.setText(ten);
    }
}
