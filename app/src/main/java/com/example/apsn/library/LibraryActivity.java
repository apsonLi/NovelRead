package com.example.apsn.library;

import android.content.Intent;


import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.LinearLayout;

import android.widget.RadioButton;


public class LibraryActivity extends AppCompatActivity implements View.OnClickListener, MeFragment.OnFragmentInteractionListener, ShelfFragment.OnFragmentInteractionListener {

    private Bundle clickchoice;
    private Intent intent;
    private ShelfFragment shelfFragment;
    private MeFragment meFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        getSupportActionBar().hide();
        init();

    }


    public void init() {
        fragmentManager = getSupportFragmentManager();
        LinearLayout bottom = (LinearLayout) findViewById(R.id.bottom);
        RadioButton rbshelf = (RadioButton) findViewById(R.id.rb_shelf);
        RadioButton rbme = (RadioButton) findViewById(R.id.rb_me);
        rbshelf.setOnClickListener(this);
        rbme.setOnClickListener(this);
        setTabSelection(0);

    }

    private void setTabSelection(int index) {


        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色

                if (shelfFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    shelfFragment = new ShelfFragment();
                    transaction.add(R.id.topPanel, shelfFragment);
                    Log.d("suc", "setTabSelection(0) returned: " + 0);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(shelfFragment);
                    Log.d("suc", "setTabSelection(0) returned: " + 0);
                }
                break;
            case 1:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.topPanel, meFragment);
                    Log.d("suc", "setTabSelection(1) returned: " + 1);
                } else {
                    Log.d("suc", "setTabSelection(1) returned: " + 1);
                    transaction.show(meFragment);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (shelfFragment != null) {
            Log.d("succ", "hideFragments:shelf ");
            transaction.hide(shelfFragment);
        }
        if (meFragment != null) {
            Log.d("succ", "hideFragments: me");
            transaction.hide(meFragment);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.rb_me:
                Log.d("succ", "onClick() called with: view = rb_me");
                setTabSelection(1);
                break;
            case R.id.rb_shelf:
                Log.d("succ", "onClick() called with: view = rb_shelf");
                setTabSelection(0);
                break;


        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
