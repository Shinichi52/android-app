package com.delaroystudios.alarmreminder.HocTap.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.HocTap.Datab.Data_h.DatabaseAccess;
import com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen.SharePreferences;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.HistoryFragment;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.LessonsFragment;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.PlayFragMent;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.SettingsFragment;
import com.delaroystudios.alarmreminder.HocTap.view.fragment.SubjectsFragment;
import com.delaroystudios.alarmreminder.HocTap.view.helper.BottomNavigationBehavior;
import com.delaroystudios.alarmreminder.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TrangChu extends AppCompatActivity {
    TextView txtChuDe1;
    DatabaseAccess databaseAccess;
    SharePreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAccess = new DatabaseAccess(this);
        databaseAccess.open();
        sharePreferences = new SharePreferences(this);
        sharePreferences.init(this);
        NgayMoi();
        txtChuDe1 = (TextView) findViewById(R.id.txtChuDe1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        txtChuDe1.setText(R.string.title_lessons);
        loadFragment(new LessonsFragment());
    }

public void NgayMoi(){
    String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    int ngay = Integer.parseInt(timeStamp.substring(0,2));
    int thang = Integer.parseInt(timeStamp.substring(3,5));
    int nam = Integer.parseInt(timeStamp.substring(6,10));
    if(nam>sharePreferences.getTHOI_GIAN_NAM()){
        sharePreferences.putTHOI_GIAN_NAM(nam);
        sharePreferences.putTHOI_GIAN_THANG(thang);
        sharePreferences.putTHOI_GIAN_NGAY(ngay);
        sharePreferences.putNGAY_MOI(0);
    }else if(thang>sharePreferences.getTHOI_GIAN_THANG()){
        sharePreferences.putTHOI_GIAN_NAM(nam);
        sharePreferences.putTHOI_GIAN_THANG(thang);
        sharePreferences.putTHOI_GIAN_NGAY(ngay);
        sharePreferences.putNGAY_MOI(0);
    }else if(ngay>sharePreferences.getTHOI_GIAN_NGAY()){
        sharePreferences.putTHOI_GIAN_NAM(nam);
        sharePreferences.putTHOI_GIAN_THANG(thang);
        sharePreferences.putTHOI_GIAN_NGAY(ngay);
        sharePreferences.putNGAY_MOI(0);
    }


}
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_lessons:
                    txtChuDe1.setText(R.string.title_lessons);
                    fragment = new LessonsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_history:
                    txtChuDe1.setText(R.string.title_history);
                    fragment = new HistoryFragment();
                    loadFragment(fragment);
                    return true;
                /*
                case R.id.navigation_play:
                    txtChuDe1.setText(R.string.title_hoc);
                    fragment = new PlayFragMent();
                    loadFragment(fragment);
                    return true;
                    */
                case R.id.navigation_subjects:
                    txtChuDe1.setText(R.string.title_subjects);
                    fragment = new SubjectsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_settings:
                    txtChuDe1.setText(R.string.title_settings);
                    fragment = new SettingsFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
