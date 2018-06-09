package com.example.apsn.library.Http.Httpconnect;

import com.example.apsn.library.Bean.ResponseBookInfo;
import com.example.apsn.library.Bean.ResponseSearchBook;
import com.google.gson.Gson;

import static com.example.apsn.library.Http.Httpconnect.httpUtil.get;

/**
 * Created by apsn on 2018/3/16.
 */

public class getInfoUtil {
    private  static ResponseBookInfo responseBookInfo;
   private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    public   static ResponseBookInfo responseInfo(String url){
        try {
        responseBookInfo=new ResponseBookInfo();
        responseType=new responseType();

        responseType=get(url);
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
            }


        }
        catch (Exception e)
        {
            throw new  RuntimeException("错误在get(url)");
        }

        String strjson=responseType.getSb().toString();
        //String strjson="{\"Code\": \"Success\", \"BookPicture\": \"http://img.sanjiangge.com/0/69/69s.jpg\", \"BookProfile\": \"    \\u5927\\u5343\\u4e16\\u754c\\uff0c\\u4f4d\\u9762\\u4ea4\\u6c47\\uff0c\\u4e07\\u65cf\\u6797\\u7acb\\uff0c\\u7fa4\\u96c4\\u835f\\u8403\\uff0c\\u4e00\\u4f4d\\u4f4d\\u6765\\u81ea\\u4e0b\\u4f4d\\u9762\\u7684\\u5929\\u4e4b\\u81f3\\u5c0a\\uff0c\\u5728\\u8fd9\\u65e0\\u5c3d\\u4e16\\u754c\\uff0c\\u6f14\\u7ece\\u7740\\u4ee4\\u4eba\\u5411\\u5f80\\u7684\\u4f20\\u5947\\uff0c\\u8ffd\\u6c42\\u7740\\u90a3\\u4e3b\\u5bb0\\u4e4b\\u8def\\u3002    \\u65e0\\u5c3d\\u706b\\u57df\\uff0c\\u708e\\u5e1d\\u6267\\u638c\\uff0c\\u4e07\\u706b\\u711a\\u82cd\\u7a79\\u3002    \\u6b66\\u5883\\u4e4b\\u5185\\uff0c\\u6b66\\u7956\\u4e4b\\u5a01\\uff0c\\u9707\\u6151\\u4e7e\\u5764\\u3002    \\u897f\\u5929\\u4e4b\\u6bbf\\uff0c\\u767e\\u6218\\u4e4b\\u7687\\uff0c\\u6218\\u5a01\\u65e0\\u53ef\\u654c\\u3002    \\u5317\\u8352\\u4e4b\\u4e18\\uff0c\\u4e07\\u5893\\u4e4b\\u5730\\uff0c\\u4e0d\\u6b7b\\u4e4b\\u4e3b\\u9547\\u5929\\u5730\\u3002    ......    \\u5c11\\u5e74\\u81ea\\u5317\\u7075\\u5883\\u800c\\u51fa\\uff0c\\u9a91\\u4e5d\\u5e7d\\u51a5\\u96c0\\uff0c\\u95ef\\u5411\\u4e86\\u90a3\\u7cbe\\u5f69\\u7edd\\u4f26\\u7684\\u7eb7\\u7ead\\u4e16\\u754c\\uff0c\\u4e3b\\u5bb0\\u4e4b\\u8def\\uff0c\\u8c01\\u4e3b\\u6c89\\u6d6e\\uff1f    \\u5927\\u5343\\u4e16\\u754c\\uff0c\\u4e07\\u9053\\u4e89\\u950b\\uff0c\\u543e\\u4e3a\\u5927\\u4e3b\\u5bb0\\u3002    ..................    ps:\\u8bf7\\u9886\\u53d6\\u4e86\\u5929\\u8695\\u571f\\u8c46\\u4e4b\\u5149\\u7684\\u8bfb\\u8005\\u8fdb\\u5165\\u5929\\u5e9c\\u795e\\u5149\\u7fa4262254501    PS\\uff1a\\u518d\\u8bf7\\u5927\\u5bb6\\u6536\\u85cf\\u4e0e\\u63a8\\u8350\\u672c\\u4e66\\uff0c\\u8c22\\u8c22\\u3002\", \"TheNewestTitle\": {\"SourcePageID\": \"wapbook-69-39621169\", \"Title\": \"\\u7b2c\\u4e00\\u5343\\u4e94\\u767e\\u56db\\u5341\\u4e5d\\u7ae0 \\u6700\\u540e\\u4e00\\u6218\"}}";

        responseBookInfo=(ResponseBookInfo) parseinfopJson(strjson);
        //return responseSearchBook.getBooks();
        System.out.print(responseBookInfo.getCode());

        return responseBookInfo;
    }

    //JSON数据解析
    public static Object parseinfopJson( String strJson){
        Gson gson=new Gson();




        //System.out.print(responseSearchBook.getBooks().get(0).getBookName());

        return   gson.fromJson(strJson,ResponseBookInfo.class);
    }
}
