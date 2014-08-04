package com.prototype.ryersonapp.Directory;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prototype.ryersonapp.R;

/**
 * Created by Tanmay on 2014-08-03.
 */
public class DirectoryResultsFragment extends Fragment {

    private View rootView;
    private ListView listView;
    private DirectoryResultsListAdapter adapter;
    private String[] names, titles, locations, extensions, emails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        names = args.getStringArray("ARRAY_NAME");
        titles = args.getStringArray("ARRAY_TITLE");
        locations = args.getStringArray("ARRAY_LOCATION");
        extensions = args.getStringArray("ARRAY_EXTENSION");
        emails = args.getStringArray("ARRAY_EMAIL");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("Search");
        rootView = inflater.inflate(R.layout.fragment_directory_results, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_directory_results);
        adapter = new DirectoryResultsListAdapter();
        listView.setAdapter(adapter);

        return rootView;
    }

    public class DirectoryResultsListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public DirectoryResultsListAdapter() {
            inflater = getActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            return names.length;
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

            View rootview = inflater.inflate(R.layout.layout_list_directory_results, null);

            TextView name = (TextView) rootview.findViewById(R.id.textview_directory_list_name);
            TextView title = (TextView) rootview.findViewById(R.id.textview_directory_list_title);
            TextView location = (TextView) rootview.findViewById(R.id.textview_directory_list_location);
            TextView extension = (TextView) rootview.findViewById(R.id.textview_directory_list_extension);
            TextView email = (TextView) rootview.findViewById(R.id.textview_directory_list_email);

            name.setText(names[i]);
            title.setText(titles[i]);
            location.setText(locations[i]);
            extension.setText(extensions[i]);
            email.setText(emails[i]);

            return rootview;
        }
    }
}
