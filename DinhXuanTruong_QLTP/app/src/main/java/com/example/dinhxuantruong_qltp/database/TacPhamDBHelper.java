package com.example.dinhxuantruong_qltp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TacPhamDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TacPhamDB";
    private static final int DB_VERSION = 1;

    public TacPhamDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE TacPham (\n" +
                "maTacPham TEXT PRIMARY KEY,\n" +
                "tenTacPham TEXT,\n" +
                "nhaXuatBan TEXT,\n" +
                "soXuatBan INTEGER,\n" +
                "soLuong INTEGER,\n" +
                "donGia REAL\n" +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TacPham");
        onCreate(db);
    }
}
