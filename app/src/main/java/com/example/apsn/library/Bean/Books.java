package com.example.apsn.library.Bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.transform.Source;

/**
 * Created by apsn on 2018/3/15.
 */

public class Books implements Serializable {
    private String BookName;
    private String Author;
    private String BookID;

    public List<com.example.apsn.library.Bean.Source> getSource() {
        return Source;
    }

    public void setSource(List<com.example.apsn.library.Bean.Source> source) {
        Source = source;
    }

    private List<com.example.apsn.library.Bean.Source> Source;
    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }



}
