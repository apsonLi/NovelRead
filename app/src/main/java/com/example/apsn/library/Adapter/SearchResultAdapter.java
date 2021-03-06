package com.example.apsn.library.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apsn.library.Bean.Books;
import com.example.apsn.library.R;



import java.util.List;

/**
 * Created by apsn on 2018/3/17.
 */

public class SearchResultAdapter extends BaseAdapter {
    private List<Books> data;
    private Context context;
    private LayoutInflater inflater;

    public SearchResultAdapter(Context context, List<Books> data){
        this.context=context;
        this.data=data;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0)
        {
            return 0;
        }
        else {
            return 1;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
        int type=getItemViewType(i);
        if(view==null)
        {
            switch (type)//可以通过 type更换item样式
        {
            case 0:
                view=inflater.inflate(R.layout.booksearchresultlist,null,false);
                holder1=new ViewHolder1();
                holder1.BookName=(TextView)view.findViewById(R.id.txt_bookName);

                holder1.BookAuthor=(TextView)view.findViewById(R.id.txt_bookAuthor);
                holder1.BookFromSource=(TextView)view.findViewById(R.id.txt_bookFromSource);

                view.setTag(holder1);
                break;
            case 1:
                view=inflater.inflate(R.layout.booksearchresultlist,null,false);
                holder2=new ViewHolder2();
                holder2.BookName=(TextView)view.findViewById(R.id.txt_bookName);

                holder2.BookAuthor=(TextView)view.findViewById(R.id.txt_bookAuthor);
                holder2.BookFromSource=(TextView)view.findViewById(R.id.txt_bookFromSource);

                view.setTag(holder2);
                break;
            default:
                break;
        }

        }
        else {
            switch (type)
            {
                case 0:
                   holder1=(ViewHolder1) view.getTag();
                    break;
                case 1:
                    holder2=(ViewHolder2)view.getTag();
                    break;
            }
        }
        switch (type)
        {
            case 0:
                holder1.BookName.setText(data.get(i).getBookName());

                holder1.BookAuthor.setText(data.get(i).getAuthor());
                holder1.BookFromSource.setText(data.get(i).getSource().get(0).getSourceName());

                break;
            case 1:
                holder2.BookName.setText(data.get(i).getBookName());

                holder2.BookAuthor.setText(data.get(i).getAuthor());
                holder2.BookFromSource.setText(data.get(i).getSource().get(0).getSourceName());

                break;

        }

        return view;
    }
    public class ViewHolder1
    {
        TextView BookName;

        TextView BookAuthor;
        TextView BookFromSource;

    }
    public class ViewHolder2
    {
        TextView BookName;

        TextView BookAuthor;
        TextView BookFromSource;

    }
}
