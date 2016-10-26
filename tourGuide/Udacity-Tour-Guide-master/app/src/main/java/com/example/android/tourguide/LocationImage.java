package com.example.android.tourguide;

/**
 * Created by benwong on 2016-07-09.
 */
public class LocationImage {
    public String name;
    public String location;
    public int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public LocationImage(String name, String location, int image) {
        this.name = name;
        this.location = location;
        this.image = image;
    }
}
