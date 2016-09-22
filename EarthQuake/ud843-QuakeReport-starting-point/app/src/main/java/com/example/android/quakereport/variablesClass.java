package com.example.android.quakereport;

/**
 * Created by asher.ansari on 9/21/2016.
 */
public class variablesClass {

    private String magnitude;
    private String date;
    private String place;
    public variablesClass(String mag, String plac, String dateA)
    {
        magnitude = mag;
        place = plac;
        date = dateA;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
}
