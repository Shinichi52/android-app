package com.delaroystudios.alarmreminder.HocTap.Datab.Data_h;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.delaroystudios.alarmreminder.HocTap.Datab.model.ChuDe2;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.Chude1;
import com.delaroystudios.alarmreminder.HocTap.Datab.model.TuHocMoiNgay;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyPC on 16/01/2018.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<Chude1> GetChuDe1(){
    List<Chude1> chude1s = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ChuDe1",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Chude1 chude1 = new Chude1();
            chude1.setMachude(cursor.getString(cursor.getColumnIndex("MaChuDe")));
            chude1.setTenChuDe(cursor.getString(cursor.getColumnIndex("TenChuDe")));
            chude1s.add(chude1);
            cursor.moveToNext();
        }
        return chude1s;
    }

    public List<ChuDe2> GetChuDe2(String machude){
        List<ChuDe2> chude2s = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ChuDe2 WHERE MaChuDe="+machude,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ChuDe2 chude2 = new ChuDe2();
            chude2.setMachuDe(cursor.getString(cursor.getColumnIndex("MaChuDe")));
            chude2.setTenChuDe(cursor.getString(cursor.getColumnIndex("TenChuDe")));
            chude2.setMaTruyVan(cursor.getString(cursor.getColumnIndex("MaTruyVan")));
            chude2.setTuDaHoc(cursor.getString(cursor.getColumnIndex("TuDaHoc")));
            chude2.setTuhomnay(cursor.getString(cursor.getColumnIndex("TuHomNay")));
            chude2s.add(chude2);
            cursor.moveToNext();
        }
        return chude2s;
    }

    public List<ChuDe2> GetChuDe2ChuaHoc(String matuchuahoc){
        List<ChuDe2> chude2s = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ChuDe2 WHERE TuDaHoc="+matuchuahoc,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ChuDe2 chude2 = new ChuDe2();
            chude2.setMachuDe(cursor.getString(cursor.getColumnIndex("MaChuDe")));
            chude2.setTenChuDe(cursor.getString(cursor.getColumnIndex("TenChuDe")));
            chude2.setMaTruyVan(cursor.getString(cursor.getColumnIndex("MaTruyVan")));
            chude2.setTuDaHoc(cursor.getString(cursor.getColumnIndex("TuDaHoc")));
            chude2.setTuhomnay(cursor.getString(cursor.getColumnIndex("TuHomNay")));
            chude2s.add(chude2);
            cursor.moveToNext();
        }
        return chude2s;
    }

    public List<ChuDe2> GetChuDe2DangHoc(String TuHomNay){
        List<ChuDe2> chude2s = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ChuDe2 WHERE TuHomNay="+TuHomNay,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ChuDe2 chude2 = new ChuDe2();
            chude2.setMachuDe(cursor.getString(cursor.getColumnIndex("MaChuDe")));
            chude2.setTenChuDe(cursor.getString(cursor.getColumnIndex("TenChuDe")));
            chude2.setMaTruyVan(cursor.getString(cursor.getColumnIndex("MaTruyVan")));
            chude2.setTuDaHoc(cursor.getString(cursor.getColumnIndex("TuDaHoc")));
            chude2.setTuhomnay(cursor.getString(cursor.getColumnIndex("TuHomNay")));
            chude2s.add(chude2);
            cursor.moveToNext();
        }
        return chude2s;
    }

    public  void themBaiHoc(String ngay,String noidung){
        ContentValues insertValues = new ContentValues();
        insertValues.put("Ngay", ngay);
        insertValues.put("NoiDung", noidung);
        database.insert("BaiHoc", null, insertValues);
    }

    public void SuaBaiHoc(String stt,String ngay,String noidung){
        ContentValues insertValues = new ContentValues();
        insertValues.put("Stt", stt);
        insertValues.put("Ngay", ngay);
        insertValues.put("NoiDung", noidung);
        database.update("BaiHoc",insertValues,"Ngay="+ngay,null);
    }

    public List<TuHocMoiNgay> GetBaiHocTrongNgay(){
        List<TuHocMoiNgay> tuHocMoiNgays = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM BaiHoc",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TuHocMoiNgay tuHocMoiNgay = new TuHocMoiNgay();
            tuHocMoiNgay.setStt(cursor.getString(cursor.getColumnIndex("Stt")));
            tuHocMoiNgay.setNgay(cursor.getString(cursor.getColumnIndex("Ngay")));
            tuHocMoiNgay.setNoidung(cursor.getString(cursor.getColumnIndex("NoiDung")));
            tuHocMoiNgays.add(tuHocMoiNgay);
            cursor.moveToNext();
        }
        return tuHocMoiNgays;
    }



    public void ThemChuDe1(String machude, String tenchude){
        ContentValues insertValues = new ContentValues();
        insertValues.put("MaChuDe", machude);
        insertValues.put("TenChuDe", tenchude);
        database.insert("ChuDe1", null, insertValues);
    }

    public void ThemChuDe2(String machude, String tenchude){
        ContentValues insertValues = new ContentValues();
        insertValues.put("MaChuDe", machude);
        insertValues.put("TenChuDe", tenchude);
        insertValues.put("TuDaHoc", "0");
        insertValues.put("TuHomNay", "0");
        database.insert("ChuDe2", null, insertValues);
    }
    public boolean XoaChuDe2(String matruyvan){
        int kiemtra = database.delete("ChuDe2","MaTruyVan="+matruyvan,null);
        if(kiemtra==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoahetChuDe2(String machude){
        int kiemtra = database.delete("ChuDe2","MaChuDe="+machude,null);
        if(kiemtra==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaChuDe1(String matruyvan){
        int kiemtra = database.delete("ChuDe1","MaChuDe="+matruyvan,null);
        if(kiemtra==1){
            return true;
        }else {
            return false;
        }
    }

    public void UpdateChuDe2(String machude,String tenchude ,String matruyvan,String tudahoc,String TuHomNay){
        ContentValues insertValues = new ContentValues();
        insertValues.put("MaChuDe", machude);
        insertValues.put("TenChuDe", tenchude);
        insertValues.put("MaTruyVan", matruyvan);
        insertValues.put("TuDaHoc", tudahoc);
        insertValues.put("TuHomNay", TuHomNay);
        database.update("ChuDe2",insertValues,"MaTruyVan="+matruyvan,null);
    }
}
