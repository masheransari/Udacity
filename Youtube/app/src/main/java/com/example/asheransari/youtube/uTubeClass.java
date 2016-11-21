package com.example.asheransari.youtube;

import android.net.Uri;
import android.widget.VideoView;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class uTubeClass {
    private String detail;
    private int imageView;
    public uTubeClass(String det,int imageView1)
    {
        this.detail = det;
        this.imageView = imageView1;
    }

    public String getDetail() {
        return detail;
    }

    public int getimageView() {
        return imageView;
    }
}
