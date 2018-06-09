package com.example.apsn.library.Bean;

import java.io.Serializable;

/**
 * Created by apsn on 2018/3/22.
 */

public class ResponseNewest implements Serializable {
    private String Code;
    private String SourcePageID;
    private String Title;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

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
