package com.example.baitapmau.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "MayTinhDB";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE MayTinh (\n" +
                "maMayTinh TEXT PRIMARY KEY,\n" +
                "tenMayTinh TEXT,\n" +
                "loaiMay TEXT,\n" +
                "hangSX TEXT,\n" +
                "soLuong INTEGER,\n" +
                "donGia REAL\n" +
                ")";
        db.execSQL(createTableSql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MayTinh");
        onCreate(db);
    }
}
