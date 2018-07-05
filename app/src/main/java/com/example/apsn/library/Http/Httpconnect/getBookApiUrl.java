package com.example.apsn.library.Http.Httpconnect;

import com.example.apsn.library.Http.Httpconnect.Impl.abstractUrlGet;

import java.util.HashMap;
import java.util.Map;

import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

/**
 * Created by apsn on 2018/3/16.
 */

public class getGetCatalogUrl extends abstractUrlGet {



    @Override
    public String makeUrl(String a, String b, String c,String d ,String e) {
        String url = "";
        switch (e){
            case "searchbook":
                try {
                    Map<String, String> map = setCommonArg();
                    //需要分情况获得键值u
                    map.put("BookName", a);
                    map.put("Action", "SearchBook");
                    url = Tools.getURL(map, getKey());
                }
                catch (Exception e1){
                    e1.printStackTrace();
                }
                return  url;

            case "getinfo" :
                try {
                    Map<String, String> map = setCommonArg();
                    //需要分情况获得键值
                    map.put("SourceName", a);
                    map.put("SourceBookID", b);
                    map.put("Action", "GetInfo");
                    url = Tools.getURL(map, getKey());

                } catch (Exception e1) {
                    System.out.print(e1.toString());
                }
                return url;
            case "getcatalog":
                try {
                    Map<String, String> map = setCommonArg();
                    //需要分情况获得键值
                    map.put("SourceName", a);
                    map.put("SourceBookID", b);
                    map.put("Action", "GetCatalog");
                    url = Tools.getURL(map, getKey());

                } catch (Exception e1) {
                    System.out.print(e1.toString());
                }
                return url;
            case "getcontent":
                try{
                    Map <String,String>map=setCommonArg();
                    //需要分情况获得键值
                    map.put("SourceName",a);
                    map.put("SourcePageID",b);
                    map.put("Action","GetContent");
                    map.put("SessionToken",d);
                    url=Tools.getURL(map,getKey());

                }
                catch(Exception e1) {
                    System.out.print(e1.toString());
                }
                return url;

            case "getnewtitle" :

                try{
                    Map <String,String>map=setCommonArg();
                    //需要分情况获得键值
                    map.put("SourceName",a);
                    map.put("SourceBookID", b);
                    map.put("Action","GetTheNewestTitle");
                    url=Tools.getURL(map,getKey());

                }
                catch(Exception e1) {
                    System.out.print(e1.toString());
                }
                return url;


        }
    return null;
    }


}

