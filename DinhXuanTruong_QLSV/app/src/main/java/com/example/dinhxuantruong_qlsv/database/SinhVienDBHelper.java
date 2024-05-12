package com.example.dinhxuantruong_qlsv.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SinhVienDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SinhVienDB";
    private static final int DB_VERSION = 1;

    public SinhVienDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE SinhVien (\n" +
                "maSinhVien TEXT PRIMARY KEY,\n" +
                "hoTen TEXT,\n" +
                "ngaySinh TEXT,\n" +
                "diaChi TEXT,\n" +
                "soDienThoai TEXT,\n" +
                "khoa TEXT,\n" +
                "lop TEXT,\n" +
                "monHoc TEXT\n" +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        onCreate(db);
    }
}
