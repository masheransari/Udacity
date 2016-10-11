package com.example.android.booklisting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


import java.util.ArrayList;

/**
 * Created by wolfgang on 03.07.16.
 */
public class BookAdapter extends ArrayAdapter<Book>
{
    private int mColorId;

    public BookAdapter(Activity aContext, ArrayList<Book> aBookList) 
    {
        super(aContext, 0, aBookList);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View bookItemView = convertView;

        if(bookItemView == null)
        {
            bookItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        final Book currentBook = getItem(position);

        TextView titleTextView = (TextView) bookItemView.findViewById(R.id.title_field);
        titleTextView.setText(currentBook.getTitle());

        TextView authorTextView = (TextView) bookItemView.findViewById(R.id.author_field);
        authorTextView.setText(currentBook.getAuthor());

        return bookItemView;
    }
}
