package com.atikur.booklistingapp;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by atikur on 7/1/16.
 */
public class Book {

    private String mTitle;
    private String[] mAuthors;

    public Book(String title, String[] authors) {
        mTitle = title;
        mAuthors = authors;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthors() {
        return TextUtils.join(", ", mAuthors);
    }
}
