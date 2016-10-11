package com.example.android.booklisting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ABHISHEK RAJ on 9/24/2016.
 */

public class BookDetailsActivity extends MenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details_listview);
        /**get the Book's Object from the parent Activity**/
        Books book = getIntent().getParcelableExtra("booksObjectBundle");
        /**Create a Custom Array List for the selected Book item**/
        ArrayList<Books> books = new ArrayList<Books>();
        books.add(new Books(book.getTitle(), book.getAuthor(), book.getPublisher(),
                book.getPageCount(), book.getDescription(), book.getRatings(),
                book.getPublishedDate(), book.getISBNType(), book.getISBNValue(), book.getBookImageResourceURL()));
        /**Create a custom Adapter Class used to populate the ListView of this Activity **/
        BookDetailsAdapter bookDetailsAdapter = new BookDetailsAdapter(BookDetailsActivity.this, books);
        ListView listView = (ListView) findViewById(R.id.book_details_listview);
        this.setTitle(book.getTitle());
        listView.setAdapter(bookDetailsAdapter);
    }
}
