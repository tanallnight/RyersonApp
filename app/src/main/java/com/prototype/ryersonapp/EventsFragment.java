package com.prototype.ryersonapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EventsFragment extends Fragment {

    private static final String TAG_EVENTS = "events";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_ORGANIZER = "organizer";
    private static final String TAG_TIME = "time";
    private static final String TAG_FROM = "from";
    private static final String TAG_TO = "to";
    private static String url = "https://dl.dropboxusercontent.com/u/69305400/json_test/";

    JSONArray events = null;
    ArrayList<HashMap<String, String>> eventList;
    private ProgressDialog progressDialog;
    private View rootView;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_events, container, false);

        eventList = new ArrayList<HashMap<String, String>>();
        listView = (ListView) rootView.findViewById(R.id.listview_events);
        new GetContacts().execute();


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", ">" + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    events = jsonObject.getJSONArray(TAG_EVENTS);

                    for (int i = 0; i < events.length(); i++) {

                        JSONObject c = events.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String description = c.getString(TAG_DESCRIPTION);
                        String organizer = c.getString(TAG_ORGANIZER);

                        JSONObject time = c.getJSONObject(TAG_TIME);
                        String time_from = time.getString(TAG_FROM);
                        String time_to = time.getString(TAG_TO);

                        HashMap<String, String> event = new HashMap<String, String>();

                        event.put(TAG_ID, id);
                        event.put(TAG_NAME, name);
                        event.put(TAG_DESCRIPTION, description);
                        event.put(TAG_ORGANIZER, organizer);
                        event.put("IMAGE", Integer.toString(R.drawable.campuslife_icons_bookstore));

                        eventList.add(event);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Could not get data from url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing())
                progressDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(getActivity(), eventList,
                    R.layout.layout_list_events,
                    new String[]{TAG_NAME, TAG_DESCRIPTION, TAG_ORGANIZER, "IMAGE"},
                    new int[]{R.id.name, R.id.email, R.id.mobile, R.id.imageview_events_list});

            listView.setAdapter(adapter);
        }
    }
}
