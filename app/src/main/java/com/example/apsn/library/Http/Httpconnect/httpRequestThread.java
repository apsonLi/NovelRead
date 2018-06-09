package com.example.apsn.library.Http.Httpconnect;

import android.content.Context;

/**
 * Created by apsn on 2018/3/18.
 */

public class httpRequestThread extends Thread {
     public Context context;

    public  httpRequestThread(Context context){
        this.context=context;
    }
}
