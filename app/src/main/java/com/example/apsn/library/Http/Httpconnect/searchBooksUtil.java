package com.example.apsn.library.Http.Httpconnect;

import android.util.Log;

import com.example.apsn.library.Bean.ResponseSearchBook;
import com.google.gson.Gson;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.get;

/**
 * Created by apsn on 2018/3/16.
 */

public class searchBooksUtil {
    private  static ResponseSearchBook responseSearchBook;
   private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    public   static ResponseSearchBook responseBooks(String url) {
        try {
            responseSearchBook = new ResponseSearchBook();
            responseType = new responseType();
            responseType = get(url);
            int count = 0;
            while (responseType.getCode() == 403) {
                count++;

                System.out.print("错误码" + responseType.getCode() + "\n");

                System.out.print("重新生成url\n");

                if (count > 20) {
                    System.out.print("服务器无法处理\n");
                    break;
                }
                responseType = get(url);
            }
        }
        catch (Exception e)
        {

            throw  new RuntimeException("get(url)错误"+url);
        }
        String strjson=responseType.getSb().toString();

        responseSearchBook=(ResponseSearchBook)parseSearchJson(strjson);
        //return responseSearchBook.getBooks();
        return responseSearchBook;
    }

    //JSON数据解析
    public static Object parseSearchJson( String strJson){
        Gson gson=new Gson();

        ResponseSearchBook responseSearchBook=gson.fromJson(strJson,ResponseSearchBook.class);

        //System.out.print(responseSearchBook.getBooks().get(0).getBookName());

        return responseSearchBook;
    }
}
