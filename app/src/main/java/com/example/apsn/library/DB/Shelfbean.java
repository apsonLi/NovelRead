package com.example.apsn.library.DB;

import java.util.Arrays;

/**
 * Created by apsn on 2018/6/18.
 */
//          "bookid varchar(50) primary key ,"
//         + "bookname varchar(20) not null,"
//         + "bookprofile text not null,"
//         + "sourcename varchar(20) not null,"
//         + "sourcebookid varchar(20) not null,"
//         + "img BLOB not null,"
//         + "newtitle varchar(50) not null"
//         + ");";
public class Shelfbean {
    private String bookid;
    private String bookname;
    private String bookprofile;
    private String sourcename;
    private String sourcebookid;
    private byte [] img;
    private String newtitle;

    @Override
    public String toString() {
        return "Shelfbean{" +
                "bookid='" + bookid + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bookprofile='" + bookprofile + '\'' +
                ", sourcename='" + sourcename + '\'' +
                ", sourcebookid='" + sourcebookid + '\'' +
                ", img=" + Arrays.toString(img) +
                ", newtitle='" + newtitle + '\'' +
                '}';
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookprofile() {
        return bookprofile;
    }

    public void setBookprofile(String bookprofile) {
        this.bookprofile = bookprofile;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getSourcebookid() {
        return sourcebookid;
    }

    public void setSourcebookid(String sourcebookid) {
        this.sourcebookid = sourcebookid;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getNewtitle() {
        return newtitle;
    }

    public void setNewtitle(String newtitle) {
        this.newtitle = newtitle;
    }

    public Shelfbean(String bookid, String bookname, String bookprofile, String sourcename, String sourcebookid, byte[] img, String newtitle) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookprofile = bookprofile;
        this.sourcename = sourcename;
        this.sourcebookid = sourcebookid;
        this.img = img;
        this.newtitle = newtitle;
    }
}
