package com.informeapps.informeryerson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutUsActivity extends FragmentActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_aboutus);
        getActionBar().setHomeButtonEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pager_aboutus_team);
        viewPager.setAdapter(new AboutUsSlideAdapter(getSupportFragmentManager()));

        // UnderlinePageIndicator underlinePageIndicator = (UnderlinePageIndicator) findViewById(R.id.viewpager_indicator_aboutus);
        //underlinePageIndicator.setViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class AboutUsPagerFragment extends Fragment {

        private int position;
        private String[] names = {"Raj", "Patrick", "Tanmay", "Shahar", "Sharmeen"};

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle b = getArguments();
            position = b.getInt("POSITION");
            Log.d("Viewpager bundle out", "" + position);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(
                    R.layout.layout_aboutus_team, container, false);

            TextView name = (TextView) rootView
                    .findViewById(R.id.textView_aboutus_team_name);

            name.setText(names[position]);

            return rootView;
        }

    }

    private class AboutUsSlideAdapter extends FragmentPagerAdapter {

        public AboutUsSlideAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new AboutUsPagerFragment();
            Bundle args = new Bundle();
            args.putInt("POSITION", i);
            fragment.setArguments(args);
            Log.d("Viewpager position", "" + i);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
