package com.mlgrier.okctourguide;

/**
 * Created by mlgrier on 8/29/16.
 */
public class Details {

    //Default details
    private String mDetailName;

    //More Information about the event/location
    private String mMoreInfo;

    // Image resource ID for the word
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Constant value that represents no image was provided for this word
    private static final int NO_IMAGE_PROVIDED = 0;

    public Details(String detailName, String moreInfo) {
        mMoreInfo = moreInfo;
        mDetailName = detailName;

    }

    public Details(String detailName, String moreInfo, int imageResourceId) {
        mDetailName = detailName;
        mMoreInfo = moreInfo;
        mImageResourceId = imageResourceId;
    }

    // Get the default translation of the word.
    public String getDetailName() {
        return mDetailName;
    }

    // Get the Miwok translation of the word.
    public String getMoreInfo() {
        return mMoreInfo;
    }

    // Create a new word object
    public int getImageResourceId() { return mImageResourceId; }

    // Returns whether or not there is an image for this word.
    public boolean hasImage() { return mImageResourceId != NO_IMAGE_PROVIDED; }

}
