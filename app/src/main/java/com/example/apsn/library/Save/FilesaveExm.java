package com.example.apsn.library.Save;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apsn.library.R;



//持久化存储之文件保存
public class FilesaveExm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_save);

        String test = "1111111111111111111";
        FIledownload.getInstance(getApplicationContext()).save("test",test);
        TextView textView = (TextView) findViewById(R.id.testfile);
        Log.d("succ", FIledownload.getInstance(getApplicationContext()).getFile("test"));
        textView.setText(FIledownload.getInstance(getApplicationContext()).getFile("test"));

    }

}

