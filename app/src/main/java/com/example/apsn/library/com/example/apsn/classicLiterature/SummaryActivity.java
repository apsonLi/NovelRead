package com.example.apsn.library.com.example.apsn.classicLiterature;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apsn.library.Bean.Books;
import com.example.apsn.library.Bean.ResponseBookInfo;
import com.example.apsn.library.Bean.ResponseNewest;
import com.example.apsn.library.Http.Httpconnect.getCatalogUtil;
import com.example.apsn.library.Http.Httpconnect.getGetCatalogUrl;
import com.example.apsn.library.Http.Httpconnect.getInfoUtil;
import com.example.apsn.library.Http.Httpconnect.getNewestUtil;
import com.example.apsn.library.Http.Httpconnect.httpRequestThread;
import com.example.apsn.library.R;
import com.example.apsn.library.com.example.apsn.read.ReadActivity;
import com.example.apsn.library.com.example.apsn.testActiviity.itemTestActivity;


import java.io.IOException;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.getImage;
import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

/**
 * 实现qq浏览器中的UI界面
  */
public class SummaryActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back;
    private ImageView novel_img;
    private TextView novel_name;
    private TextView novel_author;
    private jaydenxiao.com.expandabletextview.ExpandableTextView novel_summary;
    private TextView novel_newest_chapter;
    private Button chapter;
    private Button read;
    private Books books;
    private Bitmap bitmap;
    private ResponseBookInfo responseBookInfo;
    private ResponseNewest responseNewest;
    private Handler mhandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100)
            {   responseBookInfo=new ResponseBookInfo();
                responseBookInfo=(ResponseBookInfo)msg.getData().getSerializable("responseinfo");
                if(responseBookInfo!=null)
                {
                    novel_summary.setText(responseBookInfo.getBookProfile());
                   // System.out.print(responseBookInfo.toString());
                    novel_newest_chapter.setText(responseBookInfo.getTheNewestTitle().getTitle());
                }
               else {
                    Toast.makeText(SummaryActivity.this,"获得responseBookInfo为空",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private jaydenxiao.com.expandabletextview.ExpandableTextView moyan;
    private jaydenxiao.com.expandabletextview.ExpandableTextView cscs;
    private jaydenxiao.com.expandabletextview.ExpandableTextView jy;
    private Intent intent;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        init();
        httprequest();
    }
    private  void init()
    {
        moyan = (jaydenxiao.com.expandabletextview.ExpandableTextView)findViewById(R.id.moyan);
        moyan.setText(getResources().getString(R.string.mysummary));
        cscs = (jaydenxiao.com.expandabletextview.ExpandableTextView) findViewById(R.id.cscs);
        cscs.setText(getResources().getString(R.string.cscssummary));
        jy = (jaydenxiao.com.expandabletextview.ExpandableTextView) findViewById(R.id.jy);
        jy.setText(getResources().getString(R.string.jysummary));
        back = (LinearLayout) findViewById(R.id.back_pre);
        novel_img = (ImageView)findViewById(R.id.Image_novel);
        novel_name = (TextView)findViewById(R.id.showName);
        novel_author = (TextView)findViewById(R.id.showauthorname);
        novel_summary = (jaydenxiao.com.expandabletextview.ExpandableTextView)findViewById(R.id.showsummary);
        novel_newest_chapter = (TextView)findViewById(R.id.newestChapter);
        chapter = (Button) findViewById(R.id.chapter);
        read = (Button)findViewById(R.id.read);
        books=(Books) getIntent().getExtras().getSerializable("Books");
        if(books!=null)
        {
            novel_name.setText(books.getBookName());
            novel_author.setText(books.getAuthor());

        }


        back.setOnClickListener(this);
        chapter.setOnClickListener(this);
        read.setOnClickListener(this);
       /*
       */



    }
    private  void httprequest()
    {

        new httpRequestThread(this)
        {

            @Override
            public void run() {
                String a=books.getSource().get(0).getSourceName();
                String b=books.getSource().get(0).getSourceBookID();
                String c="GetInfo";
                String d=getKey();
                //发起请求
                String url =  new getGetCatalogUrl().makeUrl(a,b,c,d);
                responseBookInfo = getInfoUtil.responseInfo(url);

                //用handle将消息发给主线程
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putSerializable("responseinfo", responseBookInfo);
                message.what=100;
                message.setData(bundle);
                mhandle.sendMessage(message);
                final String img_url=responseBookInfo.getBookPicture();

                new httpRequestThread(context){
                    @Override
                    public void run() {
                        try {
                            byte[] data = getImage(img_url);
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            novel_img.post(new Runnable() {
                                @Override
                                public void run() {
                                    novel_img.setImageBitmap(bitmap);
                                    System.out.print("获取图片成功");
                                }
                            });
                        }
                        catch (Exception e)
                        {
                            throw new RuntimeException("获取图片错误");
                        }

                    }
                }.start();


            }
  }.start();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.back_pre:
                    SummaryActivity.this.finish();
                break;
            case R.id.chapter:
                intent = new Intent();
                intent.setClass(SummaryActivity.this,itemTestActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("summary2chapter",books);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.read:
                 intent =new Intent();
                intent.setClass(SummaryActivity.this, ReadActivity.class);
                bundle=new Bundle();
                intent.putExtra("action","summary");
                bundle.putSerializable("summary2read",books);
                intent.putExtras(bundle);
                startActivity(intent);
                break;



        }
    }


}
