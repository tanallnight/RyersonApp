package com.prototype.ryersonapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Tanmay on 2014-07-01.
 */
public class CampusLifeFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View rootView;
    private ListView mListView;
    private CampusLifeListAdapter adapter;
    private String[] listTitles = {"My Schedule", "Reminders", "Directory", "Bookstore"};
    private int[] listImages = {R.drawable.campuslife_icons_schedule, R.drawable.campuslife_icons_reminders,
            R.drawable.campuslife_icons_directory, R.drawable.campuslife_icons_bookstore};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife, container, false);
        View header = inflater.inflate(R.layout.layout_campuslife_list_header, mListView, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_campuslife);
        adapter = new CampusLifeListAdapter(getActivity().getLayoutInflater());

        ImageView headerImage = (ImageView) header.findViewById(R.id.imageview_campuslife_list_header);
        Picasso.with(getActivity()).load(R.raw.campuslife_header).into(headerImage);

        mListView.addHeaderView(header);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("CampusLife List", "" + i);
    }

    private class CampusLifeListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public CampusLifeListAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return listTitles.length;
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

            view = inflater.inflate(R.layout.layout_list_campuslife, null);

            TextView title = (TextView) view.findViewById(R.id.textview_campuslife_list);
            title.setText(listTitles[i]);

            ImageView image = (ImageView) view.findViewById(R.id.imageview_campuslife_list);
            Picasso.with(getActivity()).load(listImages[i]).into(image);

            return view;
        }
    }
}
