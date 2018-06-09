package com.example.apsn.library.com.example.apsn.testActiviity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apsn.library.Adapter.testBookCatalogAdapter;
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
import com.example.apsn.library.View.Fsgifview;
import com.example.apsn.library.com.example.apsn.read.ChapterReadActivity;
import com.example.apsn.library.com.example.apsn.read.ReadActivity;

import java.util.List;

import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

public class itemTestActivity extends AppCompatActivity {
    private  static ResponseGetCatalog  responseGetCatalog;
    private static List<Catalog> Catalog;
    private static String code;
    private static Books books;
    private static Bundle bundle;
    private  static ListView catalog;
    private static Context context;
    private  static ResponseGetContent m;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        if(msg.what==100)
        {
            String code=msg.getData().getString("Code");
            linearLayouttest3.setVisibility(View.GONE);
            Toast.makeText(itemTestActivity.this,code,Toast.LENGTH_SHORT).show();
        }
        }
    };
    private LinearLayout linearLayouttest3;
    private Fsgifview test4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_test);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }
        init();
        getdata();
        catalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new httpRequestThread(context)
                {

                    private Intent intent;

                    @Override
                    public void run() {
                        String a=books.getSource().get(0).getSourceName();
                        String b=Catalog.get(i).getSourcePageID();
                        String c="GetContent";
                        String d=getKey();
                        String  url=new getGetContentUrl().makeUrl(a,b,c,d);
                        m = new ResponseGetContent();
                        m =getContentUtil.responseContent(url);

                       // Toast.makeText(context,m.getCode(),Toast.LENGTH_SHORT).show();

                        bundle = new Bundle();
                        bundle.putSerializable("chapteritem2read",m);
                        intent = new Intent();
                        intent.putExtras(bundle);

                        intent.setClass(itemTestActivity.this, ChapterReadActivity.class);
                        startActivity(intent);

                    }


                }.start();
            }
        });

    }
    public void init()
    {
        code="";
        context=this;
        catalog = (ListView) findViewById(R.id.catlogList);
        linearLayouttest3 = (LinearLayout)findViewById(R.id.test3);
        linearLayouttest3.setVisibility(View.VISIBLE);
        test4 = (Fsgifview)findViewById(R.id.test4);
        test4.setImageResource(R.drawable.timg);


    }

    public void getdata()
    {
        responseGetCatalog=new ResponseGetCatalog();
        bundle = getIntent().getExtras();
        books = (Books) bundle.getSerializable("summary2chapter");

        if(books !=null)
        {

            Toast.makeText(this,"请等待",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"NUllPointerror",Toast.LENGTH_SHORT).show();
        }


        new httpRequestThread(this){
            @Override
            public void run() {


                String a= books.getSource().get(0).getSourceName();
                String b= books.getSource().get(0).getSourceBookID();
                String c="GetCatalog";
                String d=getKey();
                getGetCatalogUrl getGetCatalogUrl=new getGetCatalogUrl();
                String url= getGetCatalogUrl.makeUrl(a,b,c,d);
                responseGetCatalog = getCatalogUtil.responseCatalog(url);

                //子线程中传递HTTP响应码到UI线程 显示
                code=responseGetCatalog.getCode();
                bundle.putString("Code",code);
                Message message=new Message();
                message.setData(bundle);
                message.what=100;
                mhandler.sendMessage(message);



                catalog.post(new Runnable() {
                    @Override
                    public void run() {

                        Catalog=responseGetCatalog.getCatalog();
                        if(Catalog!=null)
                        {
                            int a=Catalog.size();
                            if(a==0)
                            {
                                Toast.makeText(itemTestActivity.this,"这本书没有任何章节 请尝试换源",Toast.LENGTH_SHORT).show();
                                itemTestActivity.this.finish();

                            }
                            else {
                                catalog.setAdapter(new testBookCatalogAdapter(Catalog,context));

                            }
                        }

                    }
                });
            }
        }.start();






    }

}
