package com.example.learningdemo.listviewupdate;

public class solider {
    private int imgId;
    private String content;

    public solider(){}

    public solider(int imgId, String content){
        this.imgId = imgId;
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public String getContent() {
        return content;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
