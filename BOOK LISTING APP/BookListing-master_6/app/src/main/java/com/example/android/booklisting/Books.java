package com.example.android.booklisting;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ABHISHEK RAJ on 9/22/2016.
 */

public class Books implements Parcelable {
    /**
     * Title of the book
     */
    private String mTitle;
    /* author of the book*/
    private String mAuthor;
    /*publisher of the book*/
    private String mPublisher;

    /*Variables for handling list view click*/
    private String mPageCount; //shows book's length
    private String mDescription;
    private int mRatings;
    private String mPublishedDate;
    private String mISBNType;
    private String mISBNValue;
    private String mBookImageResourceURL;

    //    private final String mImageLink;
    //    private final String mPreviewLink;

    /**
     * Create an empty constructor so that an empty Book's object can be referenced
     * in the MainActivity for storing book's info
     */
    public Books() {
    }

    /**
     * Constructs a new {@link Books}.
     *
     * @param bookTitle  is the title of the book
     * @param bookAuthor is the author
     * @param publisher  is the publisher of the book
     */
    public Books(String bookTitle, String bookAuthor, String publisher) {
        mTitle = bookTitle;
        mAuthor = bookAuthor;
        mPublisher = publisher;
    }

    /**
     * Constructs a new {@link Books} for Book's details Intnt
     *
     * @param bookTitle            is the title of the book
     * @param bookAuthor           is the author
     * @param publisher            is the publisher of the book
     * @param pageCount            is the page count of the book
     * @param description          is the description of the book
     * @param ratings              is the ratings of the book
     * @param publishedDate        is the published date of the book
     * @param iSBNType             is the ISBN Type of the book
     * @param iSBNValue            is the ISBN Value of the book
     * @param bookImageResourceURL is the Book's Image Resource URL of the book
     */
    //Used to store all the info of the selected Book on List item click on the MainActivity
    public Books(String bookTitle, String bookAuthor, String publisher, String pageCount,
                 String description, int ratings, String publishedDate, String iSBNType,
                 String iSBNValue, String bookImageResourceURL) {
        mTitle = bookTitle;
        mAuthor = bookAuthor;
        mPublisher = publisher;
        mPageCount = pageCount;
        mDescription = description;
        mRatings = ratings;
        mPublishedDate = publishedDate;
        mISBNType = iSBNType;
        mISBNValue = iSBNValue;
        mBookImageResourceURL = bookImageResourceURL;
    }

    /**
     * The Setters Methods
     */
    public void setBookImageResourceURL(String bookImageResourceURL) {
        mBookImageResourceURL = bookImageResourceURL;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public void setPageCount(String pageCount) {
        mPageCount = pageCount;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setRatings(int ratings) {
        mRatings = ratings;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public void setISBNType(String isbnType) {
        mISBNType = isbnType;
    }

    public void setISBNValue(String isbnValue) {
        mISBNValue = isbnValue;
    }

    public String getBookImageResourceURL() {
        return mBookImageResourceURL;
    }

    /**
     * The Getters Methods
     */
    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getPageCount() {
        return mPageCount;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getRatings() {
        return mRatings;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getISBNType() {
        return mISBNType;
    }

    public String getISBNValue() {
        return mISBNValue;
    }

    /**
     * Make Parcelabe Work Through these methods
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mBookImageResourceURL);
        out.writeString(mTitle);
        out.writeString(mAuthor);
        out.writeString(mPublisher);
        out.writeString(mPageCount);
        out.writeString(mDescription);
        out.writeInt(mRatings);
        out.writeString(mPublishedDate);
        out.writeString(mISBNType);
        out.writeString(mISBNValue);
    }

    private Books(Parcel in) {
        mBookImageResourceURL = in.readString();
        mTitle = in.readString();
        mAuthor = in.readString();
        mPublisher = in.readString();
        mPageCount = in.readString();
        mDescription = in.readString();
        mRatings = in.readInt();
        mPublishedDate = in.readString();
        mISBNType = in.readString();
        mISBNValue = in.readString();
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Books> CREATOR
            = new Parcelable.Creator<Books>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };
}