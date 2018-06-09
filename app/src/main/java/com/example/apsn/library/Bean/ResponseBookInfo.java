package com.example.apsn.library.Bean;

import java.io.Serializable;

/**
 * Created by apsn on 2018/3/22.
 */

public class ResponseBookInfo implements Serializable{

    /**
     * Code : Success
     * BookPicture : http://img.sanjiangge.com/0/69/69s.jpg
     * BookProfile :     大千世界，位面交汇，万族林立，群雄荟萃，一位位来自下位面的天之至尊，在这无尽世界，演绎着令人向往的传奇，追求着那主宰之路。    无尽火域，炎帝执掌，万火焚苍穹。    武境之内，武祖之威，震慑乾坤。    西天之殿，百战之皇，战威无可敌。    北荒之丘，万墓之地，不死之主镇天地。    ......    少年自北灵境而出，骑九幽冥雀，闯向了那精彩绝伦的纷纭世界，主宰之路，谁主沉浮？    大千世界，万道争锋，吾为大主宰。    ..................    ps:请领取了天蚕土豆之光的读者进入天府神光群262254501    PS：再请大家收藏与推荐本书，谢谢。
     * TheNewestTitle : {"SourcePageID":"wapbook-69-39621169","Title":"第一千五百四十九章 最后一战"}
     */

    private String Code;

    @Override
    public String toString() {
        return "ResponseBookInfo{" +
                "Code='" + Code + '\'' +
                ", BookPicture='" + BookPicture + '\'' +
                ", BookProfile='" + BookProfile + '\'' +
                ", TheNewestTitle=" + TheNewestTitle +
                '}';
    }

    private String BookPicture;
    private String BookProfile;
    private TheNewestTitleBean TheNewestTitle;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getBookPicture() {
        return BookPicture;
    }

    public void setBookPicture(String BookPicture) {
        this.BookPicture = BookPicture;
    }

    public String getBookProfile() {
        return BookProfile;
    }

    public void setBookProfile(String BookProfile) {
        this.BookProfile = BookProfile;
    }

    public TheNewestTitleBean getTheNewestTitle() {
        return TheNewestTitle;
    }

    public void setTheNewestTitle(TheNewestTitleBean TheNewestTitle) {
        this.TheNewestTitle = TheNewestTitle;
    }

    public static class TheNewestTitleBean {
        /**
         * SourcePageID : wapbook-69-39621169
         * Title : 第一千五百四十九章 最后一战
         */

        private String SourcePageID;
        private String Title;

        public String getSourcePageID() {
            return SourcePageID;
        }

        public void setSourcePageID(String SourcePageID) {
            this.SourcePageID = SourcePageID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }
    }
}
