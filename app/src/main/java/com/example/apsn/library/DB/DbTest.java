package com.example.apsn.library.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.apsn.library.DB.SQliteDbHelper.TABLE_Name;

/**
 * Created by apsn on 2018/6/18.
 */

public class DbTest  {
    private  static List<Shelfbean> list_shelfbeen;
    private  static   SQLiteDatabase database;
    private final SQliteDbHelper sQliteDbHelper;
    //数据库单例


    private  DbTest(Context context){
        try{
            sQliteDbHelper = new SQliteDbHelper(context);
            database = sQliteDbHelper.getWritableDatabase();

        }
        catch (SQLException e){
            throw e;
        }

    }
    public static SQLiteDatabase getdb (Context context){
        DbTest dbTest =new DbTest(context);
        return database;
    }

    // ShelfBean 工厂
    public  static  Shelfbean mockShelfBean(String name , byte [] img , String newtitle){

        Shelfbean sb = new Shelfbean(name, img , newtitle);

        return sb;
    }

    // 将 student 对象的值存储到 ContentValues 中
    public static ContentValues ShelfToContentValues(Shelfbean sb) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", sb.getName());
        contentValues.put("img", sb.getImg());
        contentValues.put("newtitle", sb.getNewtitle());
        return contentValues;
    }

    public static void insertShelf(ContentValues values) {



        database.insert(TABLE_Name, null, values);

    }
    public static List<Shelfbean> queryShelf(){
        Cursor cursor =database.query(TABLE_Name,null,null,null,null,null,null,null);
        // 不断移动光标获取值
        while (cursor.moveToNext()) {
            // 直接通过索引获取字段值

            list_shelfbeen =new ArrayList<Shelfbean>();
            // 先获取 name 的索引值，然后再通过索引获取字段值
            String name = cursor.getString(cursor.getColumnIndex("name"));
            byte [] img = cursor.getBlob(cursor.getColumnIndex("img"));
            String newtitle = cursor.getString(cursor.getColumnIndex("newtitle"));
            list_shelfbeen.add(mockShelfBean(name,img,newtitle));
            Log.e("succ", mockShelfBean(name,img,newtitle).toString());
        }


        cursor.close();
        return  list_shelfbeen;
    }

}
