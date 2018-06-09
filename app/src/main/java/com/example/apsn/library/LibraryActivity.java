package com.example.apsn.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apsn.library.Dialog.MyDialogFragment;
import com.example.apsn.library.com.example.apsn.classicLiterature.classicLiteratureActivity;


public class LibraryActivity extends AppCompatActivity implements View.OnClickListener{

    private Bundle clickchoice;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        getSupportActionBar().hide();
        init();
   }

    public void showDialog(){
        MyDialogFragment dialogFragment=new MyDialogFragment();
        dialogFragment.show(getFragmentManager(),"dialog");
    }
    public void init()
    {
        LinearLayout classicLiterature=(LinearLayout)findViewById(R.id.classicLiterature);
        LinearLayout networkNovel=(LinearLayout)findViewById(R.id.networkNovel);
        LinearLayout commingSoon=(LinearLayout)findViewById(R.id.commingSoon);
        classicLiterature.setOnClickListener(this);
        networkNovel.setOnClickListener(this);
        commingSoon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.classicLiterature:
                Toast.makeText(LibraryActivity.this,"a",Toast.LENGTH_SHORT).show();
                clickchoice = new Bundle();
                clickchoice.putString("choice","classicLiterature");
                intent = new Intent();
                intent.setClass(LibraryActivity.this,classicLiteratureActivity.class);
                intent.putExtras(clickchoice);
                startActivity(intent);
                break;
            case  R.id.networkNovel:
                Toast.makeText(LibraryActivity.this,"b",Toast.LENGTH_LONG).show();
                clickchoice = new Bundle();
                clickchoice.putString("choice","networkNovel");
                intent = new Intent();
                intent.setClass(LibraryActivity.this,classicLiteratureActivity.class);
                intent.putExtras(clickchoice);
                startActivity(intent);
                break;
            case    R.id .commingSoon:
                clickchoice = new Bundle();
                clickchoice.putString("choice","commingSoon");
                intent = new Intent();
                intent.setClass(LibraryActivity.this,classicLiteratureActivity.class);
                intent.putExtras(clickchoice);
                Toast.makeText(LibraryActivity.this,"c",Toast.LENGTH_LONG).show();
                break;


    }
    }
}
