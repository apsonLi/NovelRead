package com.example.apsn.library.Http.Httpconnect;

/**
 * Created by apsn on 2018/3/15.
 */

public class responseType {
    private StringBuilder sb;
    private int code;

    public responseType(StringBuilder sb, int code) {
        this.sb = sb;
        this.code = code;
    }

    public responseType() {
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
