package com.example.apsn.library.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apsn on 2018/3/19.
 */

public class ResponseGetCatalog implements Serializable {
    private String Code;
    private List<Catalog> Catalog;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<com.example.apsn.library.Bean.Catalog> getCatalog() {
        return Catalog;
    }

    public void setCatalog(List<com.example.apsn.library.Bean.Catalog> catalog) {
        Catalog = catalog;
    }
}
