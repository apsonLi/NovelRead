package com.example.apsn.library.viewPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apsn.library.R;

/**
 * Created by apsn on 2018/3/6.
 */

public class Fragment2 extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_2, container, false);
            return view;
        }


}
