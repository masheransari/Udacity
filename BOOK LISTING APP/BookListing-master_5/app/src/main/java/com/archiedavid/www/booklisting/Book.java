package com.archiedavid.www.booklisting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Archie David on 11/09/2016.
 */
public class Book implements Parcelable {

    private String mBookTitle;
    private String mBookAuthor;

    public Book(String bookTitle, String bookAuthor) {
        mBookTitle = bookTitle;
        mBookAuthor = bookAuthor;
    }

    public String getMBookTitle() {
        return mBookTitle;
    }

    public String getMBookAuthor() {
        return mBookAuthor;
    }

    protected Book(Parcel in) {
        mBookTitle = in.readString();
        mBookAuthor = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBookTitle);
        dest.writeString(mBookAuthor);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}