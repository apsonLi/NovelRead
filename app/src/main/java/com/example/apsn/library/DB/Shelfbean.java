package com.example.apsn.library.DB;

import java.util.Arrays;

/**
 * Created by apsn on 2018/6/18.
 */

public class Shelfbean {
    private String name;
    private byte [] img;
    private String newtitle;

    @Override
    public String toString() {
        return "Shelfbean{" +
                "name='" + name + '\'' +
                ", img=" + Arrays.toString(img) +
                ", newtitle='" + newtitle + '\'' +
                '}';
    }

    public Shelfbean(String name, byte[] img, String newtitle) {
        this.name = name;
        this.img = img;
        this.newtitle = newtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
