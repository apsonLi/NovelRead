package com.example.apsn.library.com.example.apsn.importtext;

/**
 * Created by apsn on 2018/3/12.
 */

public interface importLocalText {
    void readtext(String filename)throws Exception;
    boolean checkPermission();
    void showDialog();

}
