package com.example.asheransari.booknew;

/**
 * Created by asher.ansari on 10/13/2016.
 */
public class Book {
    private String title;
    private String author;

    public Book(String mtitle, String mAuthor)
    {
        title = mtitle;
        author = mAuthor;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
