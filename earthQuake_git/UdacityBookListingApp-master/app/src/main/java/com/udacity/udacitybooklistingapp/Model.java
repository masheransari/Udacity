package com.udacity.udacitybooklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by temp on 15/08/2016.
 */
public class Model implements Parcelable {

    private int totalItems;
    private String id;
    private String authors;
    private String title;

    public Model(Parcel input){
        id=input.readString();
        authors=input.readString();
        title=input.readString();
    }

    public Model(int totalItems) {
        this.totalItems = totalItems;
    }

    public Model(int totalItems, String id) {
        this.totalItems = totalItems;
        this.id = id;
    }

    public Model(int totalItems, String id, String authors) {
        this.totalItems = totalItems;
        this.id = id;
        this.authors = authors;
    }

    public Model(String authors, String title) {
        this.authors = authors;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public String getAuthors() {
        return authors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(authors);
        dest.writeString(title);

    }

    public static final Parcelable.Creator<Model> CREATOR
            = new Parcelable.Creator<Model>() {
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        public Model[] newArray(int size) {
            return new Model[size];
        }
    };


}
