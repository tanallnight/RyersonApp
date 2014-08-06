package com.prototype.ryersonapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Shahar on 2014-08-05.
 */
public class DayView extends Fragment {

    MyScheduleActivity My= new MyScheduleActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.day_view, container, false);
        TextView t1= (TextView)rootView.findViewById(R.id.Date);
        t1.setText(My.getCurrentItem()+"");


        return rootView;
    }



}

