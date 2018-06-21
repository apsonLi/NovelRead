package com.example.apsn.library.DB;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apsn.library.R;

import java.io.ByteArrayOutputStream;
import java.util.List;
//bookid,  bookname,  bookprofile,  sourcename,  sourcebookid, img, newtitle
public class DbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        SQLiteDatabase db = DbUtil.getdb(getApplicationContext());
        TextView dbt = (TextView) findViewById(R.id.testdb);
        if(db == null){
            Log.d("error", "Dbtest.getdb has nullpointExceptino");
        }
        else {
            //将drawable转化为Bitmap
            Bitmap bmp = BitmapFactory.decodeResource(getResources() , R.drawable.logo);
            //第二步，声明并创建一个输出字节流对像
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //第三部 调用compress将Bitmap对象压缩为PNG格式，第一个参数是格式,第二个参数为PNG图片质量，第三个参数为接收容器，即输出字节流os
            bmp.compress(Bitmap.CompressFormat.PNG,100,os);
            //第四步，将输出字节流转换为字节数组，并进行存储数据库操作，注意，所对应的列的数据类型应该是BLOB类型
            byte  [] img = os.toByteArray();

            Log.d("succ", DbUtil.mockShelfBean("bookid","bookname","bookprofile","sourcename","sourcebookid",  img , "newtest").toString());

            DbUtil.insertShelf(DbUtil.ShelfToContentValues(DbUtil.mockShelfBean("bookid","bookname","bookprofile","sourcename","sourcebookid",  img , "newtest")));

        }
        List <Shelfbean> test =  DbUtil.queryShelf();
        Shelfbean sb =test.get(0);
        dbt.setText(sb.toString());
    }
}
