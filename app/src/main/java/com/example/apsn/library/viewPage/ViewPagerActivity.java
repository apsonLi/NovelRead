package com.example.apsn.library.viewPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.example.apsn.library.LibraryActivity;
import com.example.apsn.library.R;
import com.example.apsn.library.SplashActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * ViewPager 引导
 *com.zhangyx.MyLauncherGuide.activity.viewPage.ViewPagerActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-21 下午4:24:29
 */
public class ViewPagerActivity extends FragmentActivity {
	private ViewPager mVPActivity;
	private Fragment1 mFragment1;
	private Fragment2 mFragment2;
	private Fragment3 mFragment3;
	private Fragment4 mFragment4;

	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_viewpager);
		initView();
		date();

	}

	private void date() {
		SharedPreferences shared = getSharedPreferences("is", MODE_PRIVATE);
		boolean isfer = shared.getBoolean("isfer", true);
		SharedPreferences.Editor editor = shared.edit();
		if (isfer) {/*
			//第一次进入跳转
			Intent in=new Intent(MainActivity.this,oneActivity.class);
			startActivity(in);
			finish();*/

			editor.putBoolean("isfer", false);
			editor.apply();
			editor.commit();

		} else {
			//第二次进入跳转
			Intent in = new Intent(this, SplashActivity.class);
			startActivity(in);
			finish();


		}
	}


	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		mFragment1 = new Fragment1();
		mFragment2 = new Fragment2();
		mFragment3 = new Fragment3();
		mFragment4 = new Fragment4();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mListFragment.add(mFragment4);
		mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
				mListFragment);
		mVPActivity.setAdapter(mPgAdapter);
	}
}
