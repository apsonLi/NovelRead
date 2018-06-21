package com.example.apsn.library;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apsn.library.DB.DbActivity;
import com.example.apsn.library.Save.FilesaveExm;

public class SplashActivity extends AppCompatActivity {
private  static  final  int DISPLAY_TIME=2000;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LibraryActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();

            }
        },DISPLAY_TIME);

    }
}
