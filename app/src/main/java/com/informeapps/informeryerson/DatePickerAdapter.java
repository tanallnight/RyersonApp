package com.informeapps.informeryerson;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Calendar;


/**
 * Created by Shahar on 2014-08-05.
 */
public class DatePickerAdapter extends FragmentPagerAdapter {


    public DatePickerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        if (index >= 0) {
            return new DayView();
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        MyScheduleActivity My = new MyScheduleActivity();
        int index = My.howLong(Calendar.getInstance()) + 1;
        return index;
    }

}



