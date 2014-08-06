package com.prototype.ryersonapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Created by Shahar on 2014-08-05.
 */
public class MyScheduleActivity extends FragmentActivity implements ActionBar.TabListener {
    protected ViewPager viewPager;
    private DatePickerAdapter mAdapter;
    private ActionBar actionBar;

   protected int CurrentItem;

   private static FloatingActionButton floatingActionButton;





    // Tab titles
    private String[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myschedule);

        tabs=new String[howLong(Calendar.getInstance())+1];
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new DatePickerAdapter(getSupportFragmentManager());
        int x=0;
        for(int p=0; p<howLong(Calendar.getInstance())+1;p++)
        {
            tabs[p]=(shiftDate(Calendar.getInstance(),x));
            x++;
        }


        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
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
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        CurrentItem=viewPager.getCurrentItem();

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
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    public String shiftDate(Calendar c, int Shift)
    {
        Calendar calendar=c;
        calendar.add(Calendar.DAY_OF_MONTH,Shift);
      //  String [] date = new String[]{new SimpleDateFormat("EEEE").format(calendar.getTime()),new SimpleDateFormat("MMMM").format(calendar.getTime()),new SimpleDateFormat("yyyy").format(calendar.getTime())};
        return (new SimpleDateFormat("EEEE").format(calendar.getTime()));
    }
    public String shiftMonth(Calendar c, int Shift)
    {
        Calendar calendar=c;
        calendar.add(Calendar.MONTH,Shift);
        //  String [] date = new String[]{new SimpleDateFormat("EEEE").format(calendar.getTime()),new SimpleDateFormat("MMMM").format(calendar.getTime()),new SimpleDateFormat("yyyy").format(calendar.getTime())};
        return (new SimpleDateFormat("MMMM").format(calendar.getTime()));
    }

    public int howLong(Calendar c)
    {
        int max=0;
        Calendar calendar=c;
        int year = calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        Calendar myCal= new GregorianCalendar(year,month,1);
        int daysInMonth = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currDay= calendar.get(Calendar.DAY_OF_MONTH);
        max=(daysInMonth-currDay);

        return max;
    }

    public int getCurrentItem() {
        return CurrentItem;
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


