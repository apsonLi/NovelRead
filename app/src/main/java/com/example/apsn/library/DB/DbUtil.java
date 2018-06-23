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

public class DbUtil {
    private  static List<Shelfbean> list_shelfbeen;
    private  static   SQLiteDatabase database;
    private final SQliteDbHelper sQliteDbHelper;
    //数据库单例


    private DbUtil(Context context){
        try{
            sQliteDbHelper = new SQliteDbHelper(context);
            database = sQliteDbHelper.getWritableDatabase();

        }
        catch (SQLException e){
            throw e;
        }

    }
    public static SQLiteDatabase getdb (Context context){
        DbUtil dbUtil =new DbUtil(context);
        return database;
    }


    public  static  Shelfbean mockShelfBean(String bookid, String bookname, String bookprofile, String sourcename, String sourcebookid, byte[] img, String newtitle){

        Shelfbean sb = new Shelfbean( bookid,  bookname,  bookprofile,  sourcename,  sourcebookid, img, newtitle);

        return sb;
    }

    // 将 student 对象的值存储到 ContentValues 中
    public static ContentValues ShelfToContentValues(Shelfbean sb) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("bookid", sb.getBookid());
        contentValues.put("bookname", sb.getBookname());
        contentValues.put("bookprofile", sb.getBookprofile());
        contentValues.put("sourcename", sb.getSourcename());
        contentValues.put("sourcebookid", sb.getSourcebookid());
        contentValues.put("img", sb.getImg());
        contentValues.put("newtitle" , sb.getNewtitle());
        return contentValues;
    }

    public static void insertShelf(ContentValues values) {



        database.insert(TABLE_Name, null, values);

    }
    //查询
    // ShelfBean 工厂
    //     "bookid varchar(50) primary key ,"
//         + "bookname varchar(20) not null,"
//         + "bookprofile text not null,"
//         + "sourcename varchar(20) not null,"
//         + "sourcebookid varchar(20) not null,"
//         + "img BLOB not null,"
//         + "newtitle varchar(50) not null"
//         + ");";
    public static List<Shelfbean> queryShelf(){
        Cursor cursor =database.query(TABLE_Name,null,null,null,null,null,null,null);
        // 不断移动光标获取值

        list_shelfbeen =new ArrayList<Shelfbean>();
        while (cursor.moveToNext()) {
            // 直接通过索引获取字段值


            // 先获取 name 的索引值，然后再通过索引获取字段值
            String bookid = cursor.getString(cursor.getColumnIndex("bookid"));
            String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
            String bookprofile = cursor.getString(cursor.getColumnIndex("bookprofile"));
            String sourcename = cursor.getString(cursor.getColumnIndex("sourcename"));
            String sourcebookid = cursor.getString(cursor.getColumnIndex("sourcebookid"));
            byte [] img = cursor.getBlob(cursor.getColumnIndex("img"));
            String newtitle = cursor.getString(cursor.getColumnIndex("newtitle"));

            list_shelfbeen.add(mockShelfBean(bookid,  bookname,  bookprofile,  sourcename,  sourcebookid, img, newtitle));
            Log.e("succ", mockShelfBean(bookid,  bookname,  bookprofile,  sourcename,  sourcebookid, img, newtitle).toString());
        }


        cursor.close();
        return  list_shelfbeen;
    }
    //删除
    public static  int delete(String name){
       return database.delete(TABLE_Name ,"bookid=?",new String []{String.valueOf(name)});
    }
    //更新
    public static  int update(String name,ContentValues contentValues){
      return   database.update("product", contentValues, "bookid=?", new String[] { String.valueOf(name) });
    }

}
