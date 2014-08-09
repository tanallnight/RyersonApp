package com.informeapps.informeryerson;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Shahar on 2014-08-05.
 */
public class MyScheduleActivity extends FragmentActivity implements ActionBar.TabListener {
    public static Calendar[] ClickedDate;
    public static int p;
    private static FloatingActionButton floatingActionButton;
    private ViewPager viewPager;
    private DatePickerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabsDates;

    public static Calendar[] getClickedDate() {
        return ClickedDate;
    }

    public static int getP() {
        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myschedule);


        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabsDates = new String[howLong(Calendar.getInstance()) + 1];
        ClickedDate = new Calendar[howLong(Calendar.getInstance()) + 2];
        actionBar = getActionBar();

        int x = 0;
        for (int q = 0; q < howLong(Calendar.getInstance()) + 1; q++) {
            tabsDates[q] = (shiftDate(Calendar.getInstance(), x));
            ClickedDate[q] = (shiftedCalender(Calendar.getInstance(), x));
            x++;
        }

        mAdapter = new DatePickerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabsDates) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }


        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
                p = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


        initializeFloatingActionButton();
    }

    private void initializeFloatingActionButton() {
        floatingActionButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_add))
                .withButtonColor(Color.parseColor("#e91e63"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                ClickedDate[ClickedDate.length - 1] = Calendar.getInstance();
                p = ClickedDate.length - 1;
            }

        });

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
        p = tab.getPosition();

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public String shiftDate(Calendar c, int Shift) {
        Calendar calendar = c;
        calendar.add(Calendar.DAY_OF_MONTH, Shift);
        //  String [] date = new String[]{new SimpleDateFormat("EEEE").format(calendar.getTime()),new SimpleDateFormat("MMMM").format(calendar.getTime()),new SimpleDateFormat("yyyy").format(calendar.getTime())};
        return (new SimpleDateFormat("EEEE").format(calendar.getTime()));
    }

    public Calendar shiftedCalender(Calendar c, int Shift) {
        Calendar calendar = c;
        calendar.add(Calendar.DAY_OF_MONTH, Shift);
        return calendar;
    }

    public String shiftMonth(Calendar c, int Shift) {
        Calendar calendar = c;
        calendar.add(Calendar.MONTH, Shift);
        //  String [] date = new String[]{new SimpleDateFormat("EEEE").format(calendar.getTime()),new SimpleDateFormat("MMMM").format(calendar.getTime()),new SimpleDateFormat("yyyy").format(calendar.getTime())};
        return (new SimpleDateFormat("MMMM").format(calendar.getTime()));
    }

    public int howLong(Calendar c) {
        int max = 0;
        Calendar calendar = c;
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Calendar myCal = new GregorianCalendar(year, month, 1);
        int daysInMonth = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);
        max = (daysInMonth - currDay);

        return max;
    }
}

/*
        TextView monthText =(TextView)findViewById(R.id.monthTextView);
        TextView dayText =(TextView)findViewById(R.id.textView2);

        Calendar cal = Calendar.getInstance();
        String monthYear = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());
        String day= new SimpleDateFormat("EEEE").format(cal.getTime());


        dayText.setText(day);
        monthText.setText(monthYear);*/


