package com.example.apsn.library.Save;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Created by apsn on 2018/6/21.
 */

public class FIledownload extends Activity{
    private static Context context;
    private static String FILE_NAME;
    private static final FIledownload fd = new FIledownload();
    private FIledownload (){

    }
    public static FIledownload getInstance(Context context1){
       context = context1;
        return fd;
    }
    public  void save(String filename,String data){
        BufferedWriter bwr =null;
        try{

           // FileOutputStream fos = openFileOutput(FILE_NAME,);
            FileOutputStream fos = context.openFileOutput(filename,MODE_APPEND);
            bwr = new BufferedWriter(new OutputStreamWriter(fos));
            bwr.write(data);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
            if(bwr!=null){
                bwr.close();

            }

            }
            catch(IOException e){e.printStackTrace();}
        }



    }
    public    String  getFile(String filename) {
        BufferedReader br = null;
        try {
            FileInputStream fin = context.openFileInput(filename);
            br =new BufferedReader(new InputStreamReader(fin));
            StringBuffer buffer = new StringBuffer();
            String len = "";
            while ((len = br.readLine()) != null) {
                buffer.append(len);
            }
            return buffer.toString();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";

        }

    }


