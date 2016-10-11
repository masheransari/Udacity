package com.developer.jc.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter for the list of books in SearchFragment
 */
public class BookListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private Book[] mBooks;

    public BookListAdapter(Context context, Book[] books) {
        super();
        this.mContext = context;
        this.mBooks = books;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(mBooks != null) {
            return mBooks.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mBooks != null) {
            return mBooks[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.bookTitle);
        title.setText(mBooks[position].getTitle());
        TextView author = (TextView) convertView.findViewById(R.id.bookAuthor);
        String authors = "";
        if(mBooks[position].getAuthors() != null) {
            for (String a : mBooks[position].getAuthors()) {
                if(authors.equals("")) {
                    authors += a;
                } else {
                    authors += ", " + a;
                }
            }
        }
        if(authors.equals("")) {
            authors = "~No Author~";
        }
        author.setText(authors);
        return convertView;
    }
}
