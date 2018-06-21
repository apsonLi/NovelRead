package com.example.apsn.library.com.example.apsn.classicLiterature;

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

import com.example.apsn.library.Adapter.SearchResultAdapter;

import com.example.apsn.library.Bean.Books;
import com.example.apsn.library.Bean.ResponseSearchBook;
import com.example.apsn.library.Http.Httpconnect.getSearchBookUrl;
import com.example.apsn.library.Http.Httpconnect.httpRequestThread;
import com.example.apsn.library.Http.Httpconnect.searchBooksUtil;
import com.example.apsn.library.R;
import com.example.apsn.library.View.Fsgifview;

import java.util.ArrayList;
import java.util.List;

import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

public class contentActivity extends AppCompatActivity {
   private List<Books> BooksList;
   private String responCode;
    private ListView listView;

    /**
     * 用handler实现数据未获取前转圈圈的画面
     */
    private Handler mhandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100)
            {
                Toast.makeText(contentActivity.this,"请等待",Toast.LENGTH_SHORT).show();
            }
            if(msg.what==200)
            {
                linearLayouttest.setVisibility(View.GONE);

               Bundle bundle= msg.getData();
                Toast.makeText(contentActivity.this,"获取数据时间"+bundle.getLong("TimeRun"),Toast.LENGTH_SHORT).show();
            }
        }
    };
    private LinearLayout linearLayouttest;
    private Fsgifview testgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        init();

    }

    public void  getBooksDate ()
    {
        responCode="";
        BooksList=new ArrayList<>();
        String a=getdata();//BookName
        String b="SearchBook";
        String c=getKey();
        getSearchBookUrl getSearchBookUrl=new getSearchBookUrl();

        String url=getSearchBookUrl.makeUrl(a,b,c);
        ResponseSearchBook responseSearchBook=new ResponseSearchBook();
        responseSearchBook=searchBooksUtil.responseBooks(url);
        //处理搜索到的书籍的数据
        //处理返回码
       responCode=responseSearchBook.getCode();
        //处理返回Books
        BooksList=responseSearchBook.getBooks();

        //System.out.print(responseSearchBook.getBooks().get(0).getBookName());
        //return  responseSearchBook.getBooks();
    }
    private String  getdata()
    {
        Intent intent=getIntent();
        if(intent!=null){
            Bundle bundle=intent.getExtras();
            String find_content="";
            if(bundle.get("find_book_name")!=null)
            {
                find_content= bundle.get("find_book_name")+"";
            }
            return find_content;

        }

        return null;

    }
  private void init()
    {
        //子线程更新UI问题 获取context需要自定义一个Thread 目前更新问题用view.post解决
        // 以及android的HTTP请求只能在子线程中发起的问题
        // 还有服务端=号编码错误问题
        linearLayouttest = (LinearLayout)findViewById(R.id.test1);
        linearLayouttest.setVisibility(View.VISIBLE);
        testgif = (Fsgifview)findViewById(R.id.test2);
        testgif.setImageResource(R.drawable.timg);
        listView =(ListView) findViewById(R.id.list_searchBook_result);
        new httpRequestThread(this){

            @Override
            public void run() {
                mhandle.sendEmptyMessage(100);
                long timepre=System.currentTimeMillis();
                getBooksDate();
                long timeout=System.currentTimeMillis();
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putLong("TimeRun",timeout-timepre);
                message.what=200;
                message.setData(bundle);
                mhandle.sendMessage(message);

                if(BooksList!=null)
                {
                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                        Toast.makeText(context,"获取"+responCode,Toast.LENGTH_LONG).show();
                            listView.setAdapter(new SearchResultAdapter(context,BooksList));

                        }
                    });
                }

        }
        }.start();

        //item点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("Books",BooksList.get(i));
                intent.putExtras(bundle);
                intent.setClass(contentActivity.this,SummaryActivity.class);
                startActivity(intent);
            }
        });

    }
}
