package com.informeapps.informeryerson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BookstoreFragment extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_campuslife_bookstore);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        setTitle("Bookstores");

        ArrayAdapter<String> BookStoreListAdapter = new ListAdapter(this, R.layout.layout_list_explore);
        BookStoreListAdapter.add("Blue");
        final ListView BookStorelistView = (ListView) findViewById(R.id.listview_bookstore);

        BookStorelistView.setAdapter(BookStoreListAdapter);
    }




    class ListAdapter extends ArrayAdapter<String> {


        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.layout_list_bookstore, null);
            }
            TextView BookStoreName =(TextView)convertView.findViewById(R.id.campuslife_BookstoreName);
            BookStoreName.setText(getItem(position));
            return convertView;
        }
    }
}
