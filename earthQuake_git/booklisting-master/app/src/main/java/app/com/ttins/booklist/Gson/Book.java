package app.com.ttins.booklist.Gson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Book implements Parcelable {
    String title;
    ArrayList<String> authors;

    public Book() {}

    public Book(Parcel in) {
        title = in.readString();
        authors = in.readArrayList(null);
    }

    public String getTitle() {
        return this.title;
    }

    public ArrayList<String> getAuthors() {
        return this.authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeList(authors);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}
