package com.archiedavid.www.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Archie David on 11/09/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.bookTitleTxtView);
        title.setText(currentBook.getMBookTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.bookAuthorTxtView);
        author.setText(currentBook.getMBookAuthor());

        return listItemView;
    }
}
