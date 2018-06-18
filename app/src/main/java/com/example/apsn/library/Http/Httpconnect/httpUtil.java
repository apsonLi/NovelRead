package com.example.apsn.library.Http.Httpconnect;


import com.example.apsn.library.Bean.ResponseSearchBook;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.apsn.library.Http.Httpconnect.sk.secretKey.getKey;

/**
 * Created by apsn on 2018/3/15.
 */


public class httpUtil {


    private static int code;
    private static BufferedReader br;
    private static InputStream is;
    private static com.example.apsn.library.Http.Httpconnect.responseType responseType;

    private static HttpURLConnection connection;

    public static byte[] getImage(String path) throws Exception {

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置连接超时为5秒
        conn.setConnectTimeout(5000);
        // 设置请求类型为Get类型
        conn.setRequestMethod("GET");
        // 判断请求Url是否成功
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = read(inStream);
        inStream.close();
        return bt;
    }


    public static com.example.apsn.library.Http.Httpconnect.responseType get(String realurl ) throws Exception {

            StringBuilder result;
            URL url;
            connection = null;
            responseType = new responseType();
                try{
                    url = new URL(realurl);

                    connection =(HttpURLConnection)url.openConnection();

                    //connection.setRequestMethod("POST");
                    //url.openConnection()默认是GET请求
                    connection.setConnectTimeout(5000);

                    connection.setDoInput(true);

                    code = connection.getResponseCode();
                    result=new StringBuilder();
                    //connection.setUseCaches(false);
                   if(code==200)
                    {
                        String line="";
                        //....
                        is = connection.getInputStream();


                        br = new BufferedReader(new InputStreamReader(is));

                        while((line=br.readLine())!=null)
                        {
                            result.append(line);
                        }

                        br.close();
                        is.close();
                    }
                    else  {

                       throw new RuntimeException("请求失败");
                   }
                     //设置状态码以及json数据
                    responseType.setCode(code);
                    responseType.setSb(result);

                    System.out.print(responseType.getSb()+"\n");


                }
                catch (IOException e)
                {
                    result=new StringBuilder();
                    String line="";
                    //....
                    is = connection.getErrorStream();

                    br = new BufferedReader(new InputStreamReader(is));

                    while((line=br.readLine())!=null)
                    {
                        result.append(line);
                    }

                    br.close();
                    is.close();
                    System.out.print(e.toString());

                }

                connection.disconnect();
                return responseType;

            }

            //从流中读取数据
            public static byte[] read(InputStream inStream) throws Exception{
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = inStream.read(buffer)) != -1)
                {
                    outStream.write(buffer,0,len);
                }
                inStream.close();
                return outStream.toByteArray();
            }

    /*
            public static  void main(String []arg) throws  Exception{
                String a="sanjiangge";
                String b=69+"";
                String c="GetInfo";
                String d=getKey();
               getGetCatalogUrl getGetCatalogUrl=new getGetCatalogUrl();
                String url=getGetCatalogUrl.makeUrl(a,b,c,d);
                System.out.print(url);
                //get(url);

            }
*/






}
