package com.delaroystudios.alarmreminder.HocTap.view.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.R;

import java.util.List;
import java.util.Locale;



public class AddChuDe2 extends AppCompatActivity {
    ImageView imvBack;
    TextView txtChuDe1;
    EditText edtnoidung;
    LinearLayout lnnghe,lnthuam,lnluu;
    TextToSpeech textToSpeech;
    DatabaseAccess databaseAccess;
    public static boolean kiemtrathem = false;
    List<ChuDe2> chuDe2s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chu_de2);
        imvBack = (ImageView) findViewById(R.id.imvBack);
        txtChuDe1 = (TextView) findViewById(R.id.txtChuDe1);
        edtnoidung = (EditText) findViewById(R.id.edtnoidung);
        lnnghe = (LinearLayout) findViewById(R.id.lnnghe);
        lnthuam = (LinearLayout) findViewById(R.id.lnthuam);
        lnluu = (LinearLayout) findViewById(R.id.lnluu);

        databaseAccess = new DatabaseAccess(this);
        databaseAccess.open();
        chuDe2s = databaseAccess.GetChuDe2(getIntent().getStringExtra("machude1"));
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtChuDe1.setText(getIntent().getStringExtra("tenchude"));

        lnnghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtnoidung.getText().toString().trim().length()>0){
                    textToSpeech = new TextToSpeech(AddChuDe2.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            textToSpeech.setLanguage(Locale.forLanguageTag("vi"));
                            textToSpeech.speak(edtnoidung.getText().toString(), TextToSpeech.QUEUE_FLUSH,null);
                        }
                    });
                }else {
                    Toast.makeText(AddChuDe2.this, "Nhập nội dung !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtnoidung.getText().toString().trim().length()>0){
                    String kiemtranoidung = edtnoidung.getText().toString().trim();
                    String mot = kiemtranoidung.toUpperCase();
                    boolean kiem = false;
                    for(int i = 0 ; i < chuDe2s.size() ; i++){
                        String kiemtradanhsach = chuDe2s.get(i).getTenChuDe();
                        String hai = kiemtradanhsach.toUpperCase().trim();
                        if(hai.equals(mot)){
                            kiem = true;
                            Toast.makeText(AddChuDe2.this, "Từ này đã có !!!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if(!kiem){
                        databaseAccess.ThemChuDe2(getIntent().getStringExtra("machude1"),edtnoidung.getText().toString().trim());
                        Toast.makeText(AddChuDe2.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        kiemtrathem = true;
                        finish();
                    }
                }else {
                    Toast.makeText(AddChuDe2.this, "Nhập nội dung !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
