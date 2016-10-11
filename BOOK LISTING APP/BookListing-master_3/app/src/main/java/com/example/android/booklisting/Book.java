package com.example.android.booklisting;

/**
 * Created by wolfgang on 04.07.16.
 */
public class Book
{
    private String mTitle;
    private String mAuthor;

    public Book(String aTitle, String aAuthor) {
        this.mTitle = aTitle;
        this.mAuthor = aAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }
}
