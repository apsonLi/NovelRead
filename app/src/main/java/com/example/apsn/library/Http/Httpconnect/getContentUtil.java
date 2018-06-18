package com.example.apsn.library.Http.Httpconnect;

import com.example.apsn.library.Bean.ResponseGetCatalog;
import com.example.apsn.library.Bean.ResponseGetContent;
import com.google.gson.Gson;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.get;

/**
 * Created by apsn on 2018/3/16.
 */

public class getContentUtil {
    private static ResponseGetContent responseGetContent;
   private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    public   static ResponseGetContent responseContent(String url){
        try {
            responseGetContent = new ResponseGetContent();
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

            throw  new RuntimeException("get（url）错误" +url);
        }
        String strjson=responseType.getSb().toString();
        responseGetContent=(ResponseGetContent) parseCatalogJson(strjson);

        return responseGetContent;
    }

    //JSON数据解析
    private static Object parseCatalogJson( String strJson){
        Gson gson=new Gson();
        ResponseGetContent responseGetContent=gson.fromJson(strJson,ResponseGetContent.class);
        return  responseGetContent;
    }
}
