package app.com.ttins.booklist.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.ttins.booklist.Gson.Book;
import app.com.ttins.booklist.R;

public class BookListAdapter extends ArrayAdapter<Book> {

    private Context context;
    ArrayList<Book> bookList;
    private static final String LOG_TAG = BookListAdapter.class.getSimpleName();

    static class ViewHolder {
        public TextView title;
        public TextView authors;
    }

    public BookListAdapter(Context context, ArrayList<Book> bookList) {
        super(context, -1, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.book_list_item_layout, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.book_title);
            viewHolder.authors = (TextView) view.findViewById(R.id.book_author);
            view.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.title.setText(bookList.get(position).getTitle());

        String authorsString = "";
        int authorNr = 0;

        for (String author:bookList.get(position).getAuthors()) {

            if (authorNr == 0) {
                authorsString = context.getString(R.string.book_item_authors_label);
            }

            if (authorNr > 0) {
                authorsString = authorsString + ", ";
            }
            authorsString = authorsString + author;
            authorNr++;
            Log.d(LOG_TAG, "Authors: " + authorsString);
        }

        viewHolder.authors.setText(authorsString);

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }
}
