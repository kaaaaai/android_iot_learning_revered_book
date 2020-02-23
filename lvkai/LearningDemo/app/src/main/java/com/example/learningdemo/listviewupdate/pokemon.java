package com.example.learningdemo.listviewupdate;

public class pokemon {
    private String pName;
    private int pImage;
    private String pDescription;


    public pokemon() {
    }

    public pokemon(int pImage,String pName, String pDescription) {
        this.pImage = pImage;
        this.pName = pName;
        this.pDescription = pDescription;
    }

    public String getpName() {
        return pName;
    }

    public int getpImage() {
        return pImage;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpImage(int pImage) {
        this.pImage = pImage;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }
}
