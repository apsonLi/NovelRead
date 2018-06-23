package com.example.apsn.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.example.apsn.library.Adapter.shelfAdapter;
import com.example.apsn.library.DB.DbUtil;
import com.example.apsn.library.DB.Shelfbean;
import com.example.apsn.library.com.example.apsn.searchActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShelfFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShelfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelfFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Shelfbean> shelfbeanList;
    private ListView bookShelf;
    private LinearLayout top;
    private ImageView search;
    private SwipeRefreshLayout refreshLayout;
    private SQLiteDatabase db;

    public ShelfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShelfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShelfFragment newInstance(String param1, String param2) {
        ShelfFragment fragment = new ShelfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shelf, container, false);
        //只有调用了这个getdb才能使用dbutil里的各个方法
        db = DbUtil.getdb(getActivity());
        init(v);

        return  v;
    }
    private  void init(View v){
        //初始化xml中的控件
        refreshLayout = v.findViewById(R.id.indexRefresh);
        top = v.findViewById(R.id.topPanel);
        bookShelf = v.findViewById(R.id.bookShelf);
        search = v.findViewById(R.id.indexSearch);
        //点击搜索按钮跳转
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),searchActivity.class);
                startActivity(intent);
            }
        });

       //下拉刷新操作
        refreshLayout.setColorSchemeColors(Color.RED);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                shelfbeanList = dbquery();

                    bookShelf.setAdapter(new shelfAdapter(shelfbeanList,getActivity()));

                refreshLayout.setRefreshing(false);
            }
        });
        //接受广播
       BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
           @Override
           public void onReceive(Context context, Intent intent) {
               shelfbeanList = dbquery();
               bookShelf.setAdapter(new shelfAdapter(shelfbeanList,getActivity()));
           }
       };
       //注册广播
        IntentFilter filter = new IntentFilter("refresh");
        getActivity().registerReceiver(broadcastReceiver, filter);
        //从数据库去出数据后渲染到listview
            shelfbeanList = dbquery();
        Log.d("list.size",shelfbeanList.size()+"");
            bookShelf.setAdapter(new shelfAdapter(shelfbeanList,getActivity()));

            bookShelf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent =new Intent();
//                intent.setClass(getActivity(),searchActivity.class);
//                startActivity(intent);
                Toast.makeText(getActivity(),shelfbeanList.get(i).getBookname(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<Shelfbean> dbquery(){

        if(db == null){
            Log.d("error", "Dbutil.getdb has nullpointExceptino");
        }
        return DbUtil.queryShelf();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
