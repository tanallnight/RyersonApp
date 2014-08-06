package com.prototype.ryersonapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Shahar on 2014-08-05.
 */
public class DayView extends Fragment {

    static View rootView;
    MyScheduleActivity My= new MyScheduleActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.day_view, container, false);
        TextView t1= (TextView)rootView.findViewById(R.id.Date);
        Button t2= (Button)rootView.findViewById(R.id.button);

        Calendar[] r = My.getClickedDate();
        int t=My.getP();
        String s = new SimpleDateFormat("EEEE").format(r[t].getTime());

        t1.setText(s);




        return rootView;
    }




}

