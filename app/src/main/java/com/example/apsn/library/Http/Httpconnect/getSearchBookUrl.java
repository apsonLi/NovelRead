package com.example.apsn.library.Http.Httpconnect;

import android.util.Log;

import com.example.apsn.library.Http.Httpconnect.Impl.abstractUrlGet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apsn on 2018/3/16.
 */

public class getSearchBookUrl extends abstractUrlGet {
    @Override
    public Map<String, String> setCommonArg() {
        Map<String, String> map = new HashMap<>();
        String randomString=(int)(Math.random()*10000000)+"";
        map.put("Format", "JSON");

        map.put("UserID", "10000001");

        map.put("SignatureMethod", "HMAC-SHA1");

        map.put("SignatureNonce", randomString);

        map.put("Version", "1.0");

        map.put("Timestamp", System.currentTimeMillis()/1000+"");

        return map;

    }

   @Override
    //查询书籍时
    public String makeUrl(String a,String b,String c) {
        String url="";
        try{
            Map <String,String>map=setCommonArg();
            //需要分情况获得键值u
            map.put("BookName",a);
            map.put("Action",b);
            url=Tools.getURL(map,c);

        }
        catch(Exception e) {
            System.out.print(e.toString());
        }
        return url;
    }


}
