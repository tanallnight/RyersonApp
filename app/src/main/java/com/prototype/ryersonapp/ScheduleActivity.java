package com.prototype.ryersonapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Tanmay on 2014-08-07.
 */
public class ScheduleActivity extends Activity {

    private ListView listView;
    private ScheduleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myschedule);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame_myschedule, new ScheduleDetailFragment())
                .commit();

        listView = (ListView) findViewById(R.id.listview_myschedule_date);
        adapter = new ScheduleListAdapter();
        listView.setAdapter(adapter);

    }

    public class ScheduleListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
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
            return getLayoutInflater().inflate(R.layout.layout_list_myschedule, null);
        }
    }
}
