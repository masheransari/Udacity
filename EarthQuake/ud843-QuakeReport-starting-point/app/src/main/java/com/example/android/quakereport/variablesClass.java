package com.example.android.quakereport;

/**
 * Created by asher.ansari on 9/21/2016.
 */
public class variablesClass {

    private double magnitude;
    private String date;
    private String place;
    public variablesClass(double mag, String plac, String dateA)
    {
        magnitude = mag;
        place = plac;
        date = dateA;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
}
