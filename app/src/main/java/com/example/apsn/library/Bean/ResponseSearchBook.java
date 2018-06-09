package com.example.apsn.library.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apsn on 2018/3/15.
 */

public class ResponseSearchBook implements Serializable {
        private  String Code;
        private List<Books> Books;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public List<com.example.apsn.library.Bean.Books> getBooks() {
        return Books;
    }

    public void setBooks(List<com.example.apsn.library.Bean.Books> books) {
        Books = books;
    }
}

