package com.example.apsn.library.Http.Httpconnect;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
//        ________  ________   __
//       / /  _/  |/  /  _/ | / /
//  __  / // // /|_/ // //  |/ /
// / /_/ // // /  / // // /|  /
// \____/___/_/  /_/___/_/ |_/
class Tools{
    private static final String Host = "http://api.codetech.top/book";
    private static String genHMAC(String data, String key) {
        byte[] result = null;
        try {
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            //用给定密钥初始化 Mac 对象
            mac.init(signinKey);
            //完成 Mac 操作
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encode(rawHmac,Base64.DEFAULT);


            //result= java.util.Base64.getEncoder().encode(rawHmac);

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        if (null != result) {

            return new String(result);
        } else {
            return null;
        }
    }
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    private static String percentEncode(String str) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(str, "UTF-8");
    }

    private static String getSignature(Map<String, String> map, String key) throws UnsupportedEncodingException {
        Map<String, String>sort_map = sortMapByKey(map);
        Iterator<Map.Entry<String, String>> entries = sort_map.entrySet().iterator();
        StringBuilder CanonicalizedQueryStringBuffer = new StringBuilder();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            CanonicalizedQueryStringBuffer.append(percentEncode(entry.getKey()));
            CanonicalizedQueryStringBuffer.append("=");
            CanonicalizedQueryStringBuffer.append(percentEncode(entry.getValue()));
            CanonicalizedQueryStringBuffer.append("&");
        }
        String CanonicalizedQueryString = CanonicalizedQueryStringBuffer.toString();
        String StringToSign= "GET" + "&" + percentEncode("/") + "&" + percentEncode(CanonicalizedQueryString.substring(0, CanonicalizedQueryString.length()-1));
        return genHMAC(StringToSign, key);
    }

    public static String getURL(Map<String, String> map, String key) throws UnsupportedEncodingException {
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        StringBuffer URLBuffer = new StringBuffer(Host + "?");
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();

            URLBuffer.append(java.net.URLEncoder.encode(entry.getKey(), "UTF-8"));

            URLBuffer.append("=");

            URLBuffer.append(java.net.URLEncoder.encode(entry.getValue(), "UTF-8"));

            URLBuffer.append("&");

        }
        URLBuffer.append("Signature=");
        //URLBuffer.append(getSignature(map, key));
        String testString=java.net.URLEncoder.encode(getSignature(map, key), "UTF-8").replace("%0A","");

        URLBuffer.append(testString);

        System.out.print(getSignature(map, key));
        return URLBuffer.toString();
    }

}

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}


