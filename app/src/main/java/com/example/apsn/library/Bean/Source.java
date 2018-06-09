package com.example.apsn.library.Bean;

import java.io.Serializable;

/**
 * Created by apsn on 2018/3/16.
 */

public class Source implements Serializable{
    private String SourceName;
    private  String SourceBookID;

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getSourceBookID() {
        return SourceBookID;
    }

    public void setSourceBookID(String sourceBookID) {
        SourceBookID = sourceBookID;
    }
}
