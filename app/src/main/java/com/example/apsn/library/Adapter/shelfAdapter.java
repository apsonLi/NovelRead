package com.example.apsn.library.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apsn.library.DB.Shelfbean;
import com.example.apsn.library.R;

import java.util.List;

/**
 * Created by apsn on 2018/3/17.
 */

public class shelfAdapter extends BaseAdapter {
    private List<Shelfbean> data;
    private Context context;
    private LayoutInflater inflater;

    public shelfAdapter(List<Shelfbean> Shelfbean, Context context)
    {
        this.context=context;
        this.data=Shelfbean;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        if(data == null){
            return 0;
        }
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
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
        int count=getCount();
        //目前根据data是否有数据从而显示没有书籍的提示没有生效，这个bug有待修复
        if(view==null)
        {
            switch (count)//可以通过 count更换item样式
            {
                case 0:
                    view=inflater.inflate(R.layout.shelfbeanlistnonestyle,null,false);
                    holder1=new ViewHolder1();
                    holder1.tips = view.findViewById(R.id.sqlNonetips);
                    view.setTag(holder1);
                    break;
                default:
                    view=inflater.inflate(R.layout.shelfbeanliststyle,null,false);
                    holder2=new ViewHolder2();
                    holder2.BookName= view.findViewById(R.id.booknameitem);
                    holder2.bookimg= view.findViewById(R.id.bookimg);
                    holder2.newtitle= view.findViewById(R.id.newtitle);
                    view.setTag(holder2);
                    break;

            }

        }
        else {
            switch (count)
            {
                case 0:
                    holder1=(ViewHolder1) view.getTag();
                    break;
                default:
                    holder2=(ViewHolder2)view.getTag();
                    break;
            }
        }
        switch (count)
        {
            case 0:
                holder1.tips.setText("请去搜索自己喜欢的书籍");
                break;
             default:
                 holder2.BookName.setText(data.get(i).getBookname());
                 byte []  tem = data.get(i).getImg();
                 holder2.bookimg.setImageBitmap(BitmapFactory.decodeByteArray(tem , 0 ,tem.length));
                 holder2.newtitle.setText(data.get(i).getNewtitle());
                break;

        }

        return view;
    }
    public class ViewHolder1
    {
        TextView tips;



    }
    public class ViewHolder2
    {
        ImageView bookimg;
        TextView newtitle;
        TextView BookName;



    }
}
