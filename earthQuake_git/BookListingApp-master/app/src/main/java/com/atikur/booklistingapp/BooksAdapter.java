package com.atikur.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by atikur on 7/1/16.
 */
public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        Book book = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_textview);
        TextView authorsTextView = (TextView) listItemView.findViewById(R.id.authors_textview);

        titleTextView.setText(book.getTitle());
        authorsTextView.setText(book.getAuthors());

        return  listItemView;
    }
}
