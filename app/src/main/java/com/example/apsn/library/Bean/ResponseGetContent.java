package com.example.apsn.library.Bean;

import java.io.Serializable;

/**
 * Created by apsn on 2018/3/22.
 */

public class ResponseGetContent implements Serializable {
    private String Code;
    private String Content;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
