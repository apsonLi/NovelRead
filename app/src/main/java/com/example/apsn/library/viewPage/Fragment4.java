package com.example.apsn.library.viewPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apsn.library.LibraryActivity;
import com.example.apsn.library.R;

/**
 * Created by apsn on 2018/3/6.
 */

public class Fragment4 extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View view = inflater.inflate(R.layout.fragment_4, container, false);

            view.findViewById(R.id.tvInNew).setOnClickListener(
                    new View.OnClickListener() {

                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            // TODO Auto-generated method stub
                            startActivity(new Intent(getActivity(),
                                    LibraryActivity.class));
                            getActivity().finish();
                        }
                    });
            return view;
        }


}
