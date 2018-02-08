package com.delaroystudios.alarmreminder.HocTap.Datab.SharePreferen;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MyPC on 18/01/2018.
 */

public class SharePreferences {
    private static final String KEY = "DEMO";
    private static final String HOA_THUONG = "HOA_THUONG";
    private static final String MAU_CHU = "MAU_CHU";
    private static final String HOC_NGAY = "HOC_NGAY";
    private static final String THEMBAIhOC = "THEMBAIhOC";
    private static final String NGAY_MOI = "NGAY_MOI";
    private static final String THOI_GIAN_NGAY = "THOI_GIAN_NGAY";
    private static final String THOI_GIAN_THANG = "THOI_GIAN_THANG";
    private static final String THOI_GIAN_NAM = "THOI_GIAN_NAM";
    private SharedPreferences sharedPreferences;
    public SharePreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    private static SharePreferences instance;
    public static SharePreferences getInstance(){
        return instance;
    }

    public static void init(Context context){
        instance = new SharePreferences(context);
    }

    public int getCHUHOA_CHUTHUONG() {
        return sharedPreferences.getInt(HOA_THUONG, 0);
    }

    public void putCHUHOA_CHUTHUONG(int id) {
        sharedPreferences.edit().putInt(HOA_THUONG, id).commit();
    }

    public int getMAU_CHU() {
        return sharedPreferences.getInt(MAU_CHU, 0);
    }

    public void putMAU_CHU(int id) {
        sharedPreferences.edit().putInt(MAU_CHU, id).commit();
    }

    public int getHOC_NGAY() {
        return sharedPreferences.getInt(HOC_NGAY, 5);
    }

    public void putHOC_NGAY(int id) {
        sharedPreferences.edit().putInt(HOC_NGAY, id).commit();
    }

    public int getTHEMBAIhOC() {
        return sharedPreferences.getInt(THEMBAIhOC, 1);
    }

    public void putTHEMBAIhOC(int id) {
        sharedPreferences.edit().putInt(THEMBAIhOC, id).commit();
    }

    public int getNGAY_MOI() {
        return sharedPreferences.getInt(NGAY_MOI, 0);
    }

    public void putNGAY_MOI(int id) {
        sharedPreferences.edit().putInt(NGAY_MOI, id).commit();
    }

    public int getTHOI_GIAN_NGAY() {
        return sharedPreferences.getInt(THOI_GIAN_NGAY, 21);
    }

    public void putTHOI_GIAN_NGAY(int id) {
        sharedPreferences.edit().putInt(THOI_GIAN_NGAY, id).commit();
    }

    public int getTHOI_GIAN_THANG() {
        return sharedPreferences.getInt(THOI_GIAN_THANG, 11);
    }

    public void putTHOI_GIAN_THANG(int id) {
        sharedPreferences.edit().putInt(THOI_GIAN_THANG, id).commit();
    }

    public int getTHOI_GIAN_NAM() {
        return sharedPreferences.getInt(THOI_GIAN_NAM, 1995);
    }

    public void putTHOI_GIAN_NAM(int id) {
        sharedPreferences.edit().putInt(THOI_GIAN_NAM, id).commit();
    }

}
