package com.developer.jc.booklisting;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SearchFragment extends android.support.v4.app.Fragment implements FetchBooks.SearchUpdate {
    private Book[] mBooks;
    private BookListAdapter mBookListAdapter;
    private ListView searchView;
    private TextView emptyView;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.getParcelableArray("key") != null) {
            mBooks = getBookArray(savedInstanceState.getParcelableArray("key"));
        }
    }

    private Book[] getBookArray(Parcelable[] p) {
        Book[] toReturn = new Book[p.length];
        for(int i = 0; i < p.length; i++) {
            toReturn[i] = (Book) p[i];
        }
        return toReturn;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = (ListView) view.findViewById(R.id.listView);
        emptyView = (TextView) view.findViewById(R.id.emptyTextView);
        if(savedInstanceState != null) {
            updateAdapter(mBooks);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray("key", mBooks);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void updateAdapter(Book[] books) {
        mBooks = books;
        mBookListAdapter = new BookListAdapter(getContext(), books);
        searchView.setAdapter(mBookListAdapter);
        searchView.setEmptyView(emptyView);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
