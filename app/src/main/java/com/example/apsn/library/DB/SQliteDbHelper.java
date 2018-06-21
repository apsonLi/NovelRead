package com.example.apsn.library.DB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apsn on 2018/6/18.
 */

public class SQliteDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";

    public static final int DB_VERSION = 1;

    public static final String TABLE_Name = "shelf";

    //创建 students 表的 sql 语句
    private static final String Shelf_CREATE_TABLE_SQL = "create table if not exists " + TABLE_Name + "("
            + "bookid varchar(50) primary key ,"
            + "bookname varchar(20) not null,"
            + "bookprofile text not null,"
            + "sourcename varchar(20) not null,"
            + "sourcebookid varchar(20) not null,"
            + "img BLOB not null,"
            + "newtitle varchar(50) not null"
            + ");";

    public SQliteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Shelf_CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        switch (i) {
            case 1:
                // do something
                break;

            default:
                break;
        }

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }
}
