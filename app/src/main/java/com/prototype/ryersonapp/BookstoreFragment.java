package com.prototype.ryersonapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class BookstoreFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private bookstoreListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife_bookstore, container, false);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setTitle("Bookstore");

        listView = (ListView) rootView.findViewById(R.id.listview_bookstore);
        adapter = new bookstoreListAdapter();
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getActivity().getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class bookstoreListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public bookstoreListAdapter(){
            inflater = getActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null){
                view = inflater.inflate(R.layout.layout_list_bookstore, null);
            }

            return view;
        }
    }
}
