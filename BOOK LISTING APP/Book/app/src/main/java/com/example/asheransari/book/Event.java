package com.example.asheransari.book;

/**
 * Created by asher.ansari on 10/11/2016.
 */
public class Event {

    private static String title;
    private static String author;

    public Event(String tit, String aut)
    {
        this.title = tit;
        this.author = aut;
    }

    public static String getAuthor() {
        return author;
    }

    public static String getTitle() {
        return title;
    }
}
