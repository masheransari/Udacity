package com.codefactoring.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        final Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String author;
        if (book.getAuthor().isEmpty()) {
            author = getContext().getString(R.string.unknown_authors);
        } else {
            author = getContext().getString(R.string.authors, book.getAuthor());
        }

        viewHolder.bookTitleView.setText(book.getTitle());
        viewHolder.bookAuthorView.setText(author);

        return convertView;
    }

    private static final class ViewHolder {
        private final TextView bookTitleView;
        private final TextView bookAuthorView;

        public ViewHolder(View view) {
            bookTitleView = (TextView) view.findViewById(R.id.text_book_title);
            bookAuthorView = (TextView) view.findViewById(R.id.text_book_author);
        }
    }
}
