package com.example.dinhxuantruong_qlsv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dinhxuantruong_qlsv.database.SinhVienDBHelper;
import com.example.dinhxuantruong_qlsv.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    private SinhVienDBHelper dbHelper;
    private SQLiteDatabase database;

    public SinhVienDAO(Context context) {
        dbHelper = new SinhVienDBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addSinhVien(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("maSinhVien", sinhVien.getMaSinhVien());
        values.put("hoTen", sinhVien.getHoTen());
        values.put("ngaySinh", sinhVien.getNgaySinh());
        values.put("diaChi", sinhVien.getDiaChi());
        values.put("soDienThoai", sinhVien.getSoDienThoai());
        values.put("khoa", sinhVien.getKhoa());
        values.put("lop", sinhVien.getLop());
        values.put("monHoc", sinhVien.getMonHoc());

        return database.insert("SinhVien", null, values);
    }

    public boolean deleteSinhVien(String maSinhVien) {
        int rowsAffected = database.delete("SinhVien", "maSinhVien = ?", new String[]{maSinhVien});
        return rowsAffected > 0;
    }

    public boolean updateSinhVien(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", sinhVien.getHoTen());
        values.put("ngaySinh", sinhVien.getNgaySinh());
        values.put("diaChi", sinhVien.getDiaChi());
        values.put("soDienThoai", sinhVien.getSoDienThoai());
        values.put("khoa", sinhVien.getKhoa());
        values.put("lop", sinhVien.getLop());
        values.put("monHoc", sinhVien.getMonHoc());

        int rowsAffected = database.update("SinhVien", values, "maSinhVien = ?", new String[]{sinhVien.getMaSinhVien()});
        return rowsAffected > 0;
    }

    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = database.rawQuery("SELECT * FROM SinhVien", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setMaSinhVien(cursor.getString(cursor.getColumnIndex("maSinhVien")));
                    sinhVien.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
                    sinhVien.setNgaySinh(cursor.getString(cursor.getColumnIndex("ngaySinh")));
                    sinhVien.setDiaChi(cursor.getString(cursor.getColumnIndex("diaChi")));
                    sinhVien.setSoDienThoai(cursor.getString(cursor.getColumnIndex("soDienThoai")));
                    sinhVien.setKhoa(cursor.getString(cursor.getColumnIndex("khoa")));
                    sinhVien.setLop(cursor.getString(cursor.getColumnIndex("lop")));
                    sinhVien.setMonHoc(cursor.getString(cursor.getColumnIndex("monHoc")));
                    sinhVienList.add(sinhVien);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SinhVienDAO", "Error while fetching SinhViens from database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return sinhVienList;
    }
}
