package com.example.asheransari.inventoryapp.variable_classes;

/**
 * Created by asher.ansari on 11/24/2016.
 */
public class variableClass {

    private String PName;
    private String mName;
    int mquantity;
    int mcost;

    public variableClass(String PName, String mName, int mquantity, int mcost) {
        this.PName = PName;
        this.mName = mName;
        this.mquantity = mquantity;
        this.mcost = mcost;
    }

    public String getPName() {
        return PName;
    }

    public String getmName() {
        return mName;
    }

    public int getMquantity() {
        return mquantity;
    }

    public int getMcost() {
        return mcost;
    }
}
