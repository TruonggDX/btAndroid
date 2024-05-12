package com.example.dinhxuantruong_qltp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dinhxuantruong_qltp.database.TacPhamDBHelper;
import com.example.dinhxuantruong_qltp.model.TacPham;

import java.util.ArrayList;
import java.util.List;

public class TacPhamDAO {
    private TacPhamDBHelper dbHelper;
    private SQLiteDatabase database;

    public TacPhamDAO(Context context) {
        dbHelper = new TacPhamDBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addTacPham(TacPham tacPham) {
        ContentValues values = new ContentValues();
        values.put("maTacPham", tacPham.getMaTacPham());
        values.put("tenTacPham", tacPham.getTenTacPham());
        values.put("nhaXuatBan", tacPham.getNhaXuatBan());
        values.put("soXuatBan", tacPham.getSoXuatBan());
        values.put("soLuong", tacPham.getSoLuong());
        values.put("donGia", tacPham.getDonGia());

        return database.insert("TacPham", null, values);
    }

    public boolean deleteTacPham(String maTacPham) {
        int rowsAffected = database.delete("TacPham", "maTacPham = ?", new String[]{maTacPham});
        return rowsAffected > 0;
    }

    public boolean updateTacPham(TacPham tacPham) {
        ContentValues values = new ContentValues();
        values.put("tenTacPham", tacPham.getTenTacPham());
        values.put("nhaXuatBan", tacPham.getNhaXuatBan());
        values.put("soXuatBan", tacPham.getSoXuatBan());
        values.put("soLuong", tacPham.getSoLuong());
        values.put("donGia", tacPham.getDonGia());

        int rowsAffected = database.update("TacPham", values, "maTacPham = ?", new String[]{tacPham.getMaTacPham()});
        return rowsAffected > 0;
    }

    public List<TacPham> getAllTacPham() {
        List<TacPham> tacPhamList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("SELECT * FROM TacPham", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    TacPham tacPham = new TacPham();
                    tacPham.setMaTacPham(cursor.getString(cursor.getColumnIndex("maTacPham")));
                    tacPham.setTenTacPham(cursor.getString(cursor.getColumnIndex("tenTacPham")));
                    tacPham.setNhaXuatBan(cursor.getString(cursor.getColumnIndex("nhaXuatBan")));
                    tacPham.setSoXuatBan(cursor.getInt(cursor.getColumnIndex("soXuatBan")));
                    tacPham.setSoLuong(cursor.getInt(cursor.getColumnIndex("soLuong")));
                    tacPham.setDonGia(cursor.getDouble(cursor.getColumnIndex("donGia")));
                    tacPhamList.add(tacPham);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("TacPhamDAO", "Error while fetching TacPhams from database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return tacPhamList;
    }
}
