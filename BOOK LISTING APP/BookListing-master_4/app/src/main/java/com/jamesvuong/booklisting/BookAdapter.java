package com.jamesvuong.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvuonger on 9/27/16.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private ArrayList<Book> mBooksList;

    public BookAdapter(Context context, ArrayList<Book> booksList) {
        super(context, 0, booksList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.book_title);
        tvTitle.setText(book.getTitle());

        TextView tvSubtitle = (TextView) convertView.findViewById(R.id.subtitle);
        tvSubtitle.setText(book.getSubtitle());

        TextView tvPublishedDate = (TextView) convertView.findViewById(R.id.published_date);
        tvPublishedDate.setText(book.getPublishedDate());

        TextView tvAuthors = (TextView) convertView.findViewById(R.id.authors);
        tvAuthors.setText(book.getAuthors());

        return convertView;
    }
}
