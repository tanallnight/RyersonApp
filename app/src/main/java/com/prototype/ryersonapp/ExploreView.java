package com.prototype.ryersonapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shahar on 2014-07-29.
 */
public class ExploreView extends Activity {


    ExploreViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_layout);
        Intent i =getIntent();
        setTitle(i.getStringExtra("Name"));


        adapter = new ExploreViewAdapter(this, generateData());


        ListView listView = (ListView) findViewById(R.id.ListView1);


        listView.setAdapter(adapter);
        String hi;


    }


    private ArrayList<Items> generateData(){
        ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Item 1"));
        return items;
    }
}
