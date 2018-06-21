package com.example.apsn.library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apsn.library.Bean.Catalog;
import com.example.apsn.library.R;

import java.util.List;

/**
 * Created by apsn on 2018/3/17.
 */

public class exampleAdapter extends BaseAdapter {
    private List<Catalog> data;
    private Context context;
    private LayoutInflater inflater;

    public exampleAdapter(List<Catalog> CatalogsList, Context context)
    {
        this.context=context;
        this.data=CatalogsList;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolderTest viewHolderTest=null;
        if(view==null)
        {
            view=inflater.inflate(R.layout.shelfbeanliststyle,null,false);
            viewHolderTest=new viewHolderTest();
            viewHolderTest.Title=view.findViewById(R.id.booknameitem);
            view.setTag(viewHolderTest);
        }
        else {
            viewHolderTest=(viewHolderTest) view.getTag();

        }
        viewHolderTest.Title.setText(data.get(i).getTitle());
        return view;
    }
    public class viewHolderTest
    {
        TextView Title;
        }
}
