package com.example.apsn.library.com.example.apsn.classicLiterature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apsn.library.R;
import com.example.apsn.library.com.example.apsn.classicLiterature.tools.EditTextClearTools;
import com.example.apsn.library.com.example.apsn.searchActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class classicLiteratureActivity extends AppCompatActivity implements View.OnClickListener {
  List <Map<String,Object>> recommbooklistdata;
    private ListView recommbooklist1;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_literature);
        getbookname();
        Intent intent=getIntent();
        String data=intent.getStringExtra("choice");
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().hide();
        }

        init();
    }
    public void init()
    {
        recommbooklist1 = (ListView)findViewById(R.id.recommbooklist);
        recommbooklist1.setAdapter(new SimpleAdapter(classicLiteratureActivity.this, recommbooklistdata,R.layout.rcommbooklist,new String[]{"bookname"},new int []{R.id.booknameitem}));
        TextView textView=(TextView) findViewById(R.id.search);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.top);
        linearLayout.setOnClickListener(this);
    }
    public void  getbookname(){
        recommbooklistdata=new ArrayList<Map<String,Object>>();
        map = new HashMap();
        map.put("bookname","活着");
        recommbooklistdata.add(map);
        map = new HashMap();
        map.put("bookname","围城");
        recommbooklistdata.add(map);
        map = new HashMap();
        map.put("bookname","兄弟");
        recommbooklistdata.add(map);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.top:
                Intent intent=new Intent();
                intent.setClass(classicLiteratureActivity.this,searchActivity.class);
                startActivity(intent);
                break;
        }
    }
}


