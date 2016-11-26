package com.example.asheransari.inventoryapp;

/**
 * Created by asher.ansari on 11/24/2016.
 */
public class variableClass {

    private String mName;
    private String pName;
    int mquantity;
    int mcost;

    public variableClass(String mName, String pName, int mquantity, int mcost) {
        this.mName = mName;
        this.pName = pName;
        this.mquantity = mquantity;
        this.mcost = mcost;
    }

    public String getmName() {
        return mName;
    }

    public String getpName() {
        return pName;
    }

    public int getMquantity() {
        return mquantity;
    }

    public int getMcost() {
        return mcost;
    }
}
