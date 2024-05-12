package com.example.baitapmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.baitapmau.database.DBHelper;
import com.example.baitapmau.model.MayTinh;

import java.util.ArrayList;
import java.util.List;

public class MayTinhDAO {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public MayTinhDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addMayTinh(MayTinh mayTinh) {
        ContentValues values = new ContentValues();
        values.put("maMayTinh", mayTinh.getMaMayTinh());
        values.put("tenMayTinh", mayTinh.getTenMayTinh());
        values.put("loaiMay", mayTinh.getLoaiMay());
        values.put("hangSX", mayTinh.getHangSX());
        values.put("soLuong", mayTinh.getSoLuong());
        values.put("donGia", mayTinh.getDonGia());

        return database.insert("MayTinh", null, values);
    }

    public boolean deleteMayTinh(String maMayTinh) {
        int rowsAffected = database.delete("MayTinh", "maMayTinh = ?", new String[]{maMayTinh});
        return rowsAffected > 0;
    }

    public boolean updateMayTinh(MayTinh mayTinh) {
        ContentValues values = new ContentValues();
        values.put("tenMayTinh", mayTinh.getTenMayTinh());
        values.put("loaiMay", mayTinh.getLoaiMay());
        values.put("hangSX", mayTinh.getHangSX());
        values.put("soLuong", mayTinh.getSoLuong());
        values.put("donGia", mayTinh.getDonGia());

        int rowsAffected = database.update("MayTinh", values, "maMayTinh = ?", new String[]{mayTinh.getMaMayTinh()});
        return rowsAffected > 0;
    }

    public List<MayTinh> getAllMayTinh() {
        List<MayTinh> mayTinhList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("SELECT * FROM MayTinh", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    MayTinh mayTinh = new MayTinh();
                    mayTinh.setMaMayTinh(cursor.getString(cursor.getColumnIndex("maMayTinh")));
                    mayTinh.setTenMayTinh(cursor.getString(cursor.getColumnIndex("tenMayTinh")));
                    mayTinh.setLoaiMay(cursor.getString(cursor.getColumnIndex("loaiMay")));
                    mayTinh.setHangSX(cursor.getString(cursor.getColumnIndex("hangSX")));
                    mayTinh.setSoLuong(cursor.getInt(cursor.getColumnIndex("soLuong")));
                    mayTinh.setDonGia(cursor.getDouble(cursor.getColumnIndex("donGia")));
                    mayTinhList.add(mayTinh);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("MayTinhDAO", "Error while fetching MayTinhs from database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return mayTinhList;
    }
}
