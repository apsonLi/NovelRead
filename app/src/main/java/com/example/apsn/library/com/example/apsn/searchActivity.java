package com.example.apsn.library.com.example.apsn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apsn.library.R;
import com.example.apsn.library.com.example.apsn.classicLiterature.classicLiteratureActivity;
import com.example.apsn.library.com.example.apsn.classicLiterature.contentActivity;
import com.example.apsn.library.com.example.apsn.classicLiterature.tools.EditTextClearTools;

public class searchActivity extends AppCompatActivity implements View.OnClickListener{

    EditText e1 ;
    ImageView m1 ;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }


        init();
    }


    private void init() {
        // TODO Auto-generated method stub
        e1 = (EditText) findViewById(R.id.book);
        b1=(Button)findViewById(R.id.button_find);
        m1 = (ImageView) findViewById(R.id.del_textbook);

        // 添加监听器
        EditTextClearTools.addclerListener(e1, m1);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(isNetworkAvailable(this))
        {
            if(view.getId()==R.id.button_find)
            {
                String find_book_name = e1.getText().toString();
                if(!find_book_name.equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("find_book_name", find_book_name);

                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(searchActivity.this, contentActivity.class);
                    startActivity(intent);
                }
                else if(find_book_name.length()==1) {
                    Toast.makeText(searchActivity.this,"不能以一个关键字搜索",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "搜索框不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(this,"请开启网络",Toast.LENGTH_LONG).show();
        }

    }
    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */

    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
