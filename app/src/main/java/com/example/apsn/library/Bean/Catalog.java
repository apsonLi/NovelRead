package com.example.apsn.library.Bean;

import java.io.Serializable;

/**
 * Created by apsn on 2018/3/19.
 */

public class Catalog implements Serializable{
    private  String SourcePageID;
    private String Title;

    public String getSourcePageID() {
        return SourcePageID;
    }

    public void setSourcePageID(String sourcePageID) {
        SourcePageID = sourcePageID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
