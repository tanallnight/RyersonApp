package com.informeapps.informeryerson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.informeapps.informeryerson.Misc.ExpandAnimation;


/**
 * Created by Shahar on 2014-08-13.
 */
public class BookstoreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        setTitle("Bookstores");

        ArrayAdapter<String> BookStoreListAdapter = new ListAdapter(this, R.layout.layout_list_explore);
        BookStoreListAdapter.add("Ryerson Campus Store");
        BookStoreListAdapter.add("Discount Textbooks");
        BookStoreListAdapter.add("Canadian Campus Bookstore");
        BookStoreListAdapter.add("Ryerson Students Union");
        final ListView BookStorelistView = (ListView) findViewById(R.id.listview_bookstore);

        BookStorelistView.setAdapter(BookStoreListAdapter);
        BookStorelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                View BookstoreExpand = view.findViewById(R.id.BookStoreExpandedInfo);
                ExpandAnimation expandAnimation = new ExpandAnimation(BookstoreExpand, getResources().getInteger(R.integer.ExpandAnimationDuration));
                BookstoreExpand.startAnimation(expandAnimation);

                BookStorelistView.smoothScrollToPositionFromTop(i, 150, getResources().getInteger(R.integer.SmoothScroolDuration));

            }
        });
    }

    public int ResourceID(String StoreName, boolean map) {
        int ResID;
        String n = StoreName.toLowerCase();
        String name = n.replaceAll("\\W", "");
        if (map) {
            name = name + "map";
        } else {
            name = name + "";
        }
        ResID = getResources().getIdentifier(name, "drawable", getPackageName());
        return ResID;
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
            TextView BookStoreName = (TextView) convertView.findViewById(R.id.campuslife_BookstoreName);
            ImageView BookStorePicture = (ImageView) convertView.findViewById(R.id.campuslife_BookstorePicture);
            ImageView BookStoreMap = (ImageView) convertView.findViewById(R.id.campuslife_BookstoreMap);
            BookStoreName.setText(getItem(position));
            BookStoreMap.setImageResource(ResourceID(getItem(position), true));

            if (getItem(position).equals("Ryerson Campus Store")) {

            } else if (getItem(position).equals("Discount Textbooks")) {

            } else if (getItem(position).equals("Canadian Campus Bookstore")) {

            } else if (getItem(position).equals("Ryerson Students Union")) {

            }

            View ExpandLayut = convertView.findViewById(R.id.BookStoreExpandedInfo);
            ((LinearLayout.LayoutParams) ExpandLayut.getLayoutParams()).bottomMargin = -200;
            ExpandLayut.setVisibility(View.GONE);

            return convertView;
        }
    }
}
