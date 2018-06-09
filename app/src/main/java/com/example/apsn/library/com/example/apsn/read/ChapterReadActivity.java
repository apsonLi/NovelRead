package com.example.apsn.library.com.example.apsn.read;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.apsn.library.Bean.Books;
import com.example.apsn.library.Bean.ResponseGetCatalog;
import com.example.apsn.library.Bean.ResponseGetContent;
import com.example.apsn.library.Http.Httpconnect.getCatalogUtil;
import com.example.apsn.library.Http.Httpconnect.getContentUtil;
import com.example.apsn.library.Http.Httpconnect.getGetCatalogUrl;
import com.example.apsn.library.Http.Httpconnect.getGetContentUrl;
import com.example.apsn.library.Http.Httpconnect.httpRequestThread;
import com.example.apsn.library.R;
import com.hw.txtreaderlib.bean.TxtMsg;
import com.hw.txtreaderlib.interfaces.ILoadListener;
import com.hw.txtreaderlib.main.TxtReaderView;

import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

public class ChapterReadActivity extends AppCompatActivity {

    private  static  Books book;
    private static Context context;
    private static  ResponseGetContent rescontent;

    private static TxtReaderView mytextview;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        init();


    }
    public void init()
    {

        context=ChapterReadActivity.this;
        mytextview = new TxtReaderView(this);
        mytextview =(TxtReaderView) findViewById(R.id.myReaderView);
        rescontent=(ResponseGetContent) getIntent().getExtras().getSerializable("chapteritem2read");
        if(rescontent!=null)
        {
            if(rescontent.getContent()!=null)
            {
                showText(replacebr(rescontent.getContent()),context);
            }

        }




    }



    public static String  replacebr(String content)
    {
        return content.replace("<br/>","\n");
    }


    public static void showText(String content, final Context context){

        mytextview.loadText(content, new ILoadListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context,"载入成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(TxtMsg txtMsg) {

            }

            @Override
            public void onMessage(String s) {

            }
        });

    }
}
