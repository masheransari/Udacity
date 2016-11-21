package com.example.asheransari.youtubeapplication;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class CustomClass {

    private String detail;
    private int imageSource;
    private String tempData;
    private float number;

    public CustomClass(String detail, int imageSource, String temp, float Num) {
        this.detail = detail;
        this.imageSource = imageSource;
        this.tempData = temp;
        this.number = Num;
    }

    public String getTempData() {
        return tempData;
    }

    public float getNumber() {
        return number;
    }

    public String getDetail() {
        return detail;
    }

    public int getImageSource() {
        return imageSource;
    }
}
