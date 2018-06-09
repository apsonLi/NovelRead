package com.example.apsn.library.com.example.apsn.read;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apsn.library.Bean.Books;
import com.example.apsn.library.Bean.Catalog;
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

public class ReadActivity extends AppCompatActivity {

    private  static  Books book;
    private static Context context;
    private static  ResponseGetCatalog rescatalog;
    private static  ResponseGetContent rescontent;
    private static Handler mhandler=new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {


                case 200:
                    rescontent = new ResponseGetContent();
                    rescontent =(ResponseGetContent) msg.getData().getSerializable("mes2hand");
                    if (rescontent!=null)
                    {
                        if(rescontent.getContent()!=null)
                        {
                            String content=replacebr(rescontent.getContent());
                            showText(content,context);
                        }
                    }
                    break;
                case 300:
                    break;
            }
        }
    };
    private static TxtReaderView mytextview;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        intent = getIntent();
        init();


    }
    public void init()
    {

        context=ReadActivity.this;
        mytextview = new TxtReaderView(this);
        mytextview =(TxtReaderView) findViewById(R.id.myReaderView);


        if(getIntent()!=null)
        {
            if(getIntent().getExtras()!=null)
            {
                book=(Books) getIntent().getExtras().getSerializable("summary2read");
                new httpRequestThread(context)
                {
                    @Override
                    public void run() {
                        super.run();
                        String a=book.getSource().get(0).getSourceName();
                        String b=book.getSource().get(0).getSourceBookID();
                        String c="GetCatalog";
                        String d=getKey();
                        String url=new  getGetCatalogUrl().makeUrl(a,b,c,d);
                        rescatalog=new ResponseGetCatalog();
                        rescatalog=getCatalogUtil.responseCatalog(url);
                        if(rescatalog!=null)
                        {
                            //改变0 可以更换章节
                            b=rescatalog.getCatalog().get(0).getSourcePageID();
                            c="GetContent";
                            url=new getGetContentUrl().makeUrl(a,b,c,d);
                            rescontent=getContentUtil.responseContent(url);

                            Message message=new Message();
                            message.what=200;
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("mes2hand",rescontent);
                            message.setData(bundle);
                            mhandler.sendMessage(message);
                        }
                    }
                }.start();
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
