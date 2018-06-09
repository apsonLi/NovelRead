package com.example.apsn.library.Http.Httpconnect;

import com.example.apsn.library.Bean.ResponseBookInfo;
import com.example.apsn.library.Bean.ResponseNewest;
import com.google.gson.Gson;

import java.io.IOException;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.get;

/**
 * Created by apsn on 2018/3/16.
 */

public class getNewestUtil {
    private  static ResponseNewest responseNewest;
   private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    public   static ResponseNewest responsenewest(String url){
        try {
       responseNewest=new ResponseNewest();

        responseType=new responseType();

        responseType=get(url);

       System.out.print(responseType.getSb());

        int count=0;
        while(responseType.getCode()==403)
        {
            count++;

            System.out.print("错误码" + responseType.getCode()+"\n" );

            System.out.print("重新生成url\n");

            if(count>20){
                System.out.print("服务器无法处理\n");
                break;
            }

                responseType =get(url);
            System.out.print(responseType.getSb());
            }


        }
        catch (IOException e)
        {

            throw new  RuntimeException("IOEX错误在get(url)");
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }
        if(responseType.getSb()!=null)
        {
            String strjson=responseType.getSb().toString();

            responseNewest=(ResponseNewest)parsenewestJson(strjson);
            //return responseSearchBook.getBooks();
            return responseNewest;
        }
        return  null;


    }

    //JSON数据解析
    public static Object parsenewestJson( String strJson){
        Gson gson=new Gson();



        //System.out.print(responseSearchBook.getBooks().get(0).getBookName());

        return gson.fromJson(strJson,ResponseNewest.class);
    }
}
