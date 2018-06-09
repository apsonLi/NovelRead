package com.example.apsn.library.com.example.apsn.importtext;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import com.example.apsn.library.Dialog.MyDialogFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by apsn on 2018/3/12.
 */

public class LocalfileManager implements importLocalText {
    public String filename;
    public String sdPath= Environment.getExternalStorageDirectory()+"test.txt";

    @Override
    public void readtext(String filename) throws Exception{
        if(!new File(sdPath).exists())
        {
            return ;
        }
        else{
            StringBuffer sb=new StringBuffer();
            FileInputStream fin=new FileInputStream(sdPath);
            String line="";
            BufferedReader br=new BufferedReader(new InputStreamReader(fin,"utf-8"));
            while((line=br.readLine())!=null)
            {
                sb.append(line);
            }
            br.close();
            fin.close();

        }
    }

    @Override
    public boolean checkPermission() {
        return false;
    }

    @Override
    public void showDialog() {

    }


}
