package com.example.apsn.library.Http.Httpconnect;

import com.example.apsn.library.Bean.ResponseGetCatalog;
import com.example.apsn.library.Bean.ResponseSearchBook;
import com.google.gson.Gson;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.get;

/**
 * Created by apsn on 2018/3/16.
 */

public class getCatalogUtil {
    private static ResponseGetCatalog responseGetCatalog;
    private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    public static ResponseGetCatalog responseCatalog(String url) {
        try {
            responseGetCatalog = new ResponseGetCatalog();
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
        } catch (Exception e) {
            throw new RuntimeException("get（url）错误");
        }
        String strjson = responseType.getSb().toString();
        responseGetCatalog = (ResponseGetCatalog) parseCatalogJson(strjson);

        return responseGetCatalog;
    }

    //JSON数据解析
    private static Object parseCatalogJson(String strJson) {
        Gson gson = new Gson();
        ResponseGetCatalog responseGetCatalog = gson.fromJson(strJson, ResponseGetCatalog.class);
        return responseGetCatalog;
    }
}
