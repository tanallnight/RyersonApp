package com.prototype.ryersonapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private DrawerListAdapter mListAdapter;
    private String[] mDrawerItems = {"Campus Life", "Student Life", "Events", "Others", "Settings", "About Us"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            getActionBar().setTitle(mDrawerItems[0]);
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .add(R.id.content_frame_main, new CampusLifeFragment())
                    .commit();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mListAdapter = new DrawerListAdapter(LayoutInflater.from(this));
        mDrawerList = (ListView) findViewById(R.id.listview_drawer);
        mDrawerList.setAdapter(mListAdapter);
        mDrawerList.setOnItemClickListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                getActionBar().setTitle(mDrawerItems[mListAdapter.getSelectedItem()]);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                getActionBar().setTitle("Ryerson App");
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        getActionBar().setTitle(mDrawerItems[i]);
        mListAdapter.setSelectedItem(i);
        mListAdapter.notifyDataSetChanged();
        mDrawerLayout.closeDrawer(mDrawerList);

        boolean isFragment = true;
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new CampusLifeFragment();
                break;
            case 1:
                fragment = new StudentLifeFragment();
                break;
            default:
                fragment = new StudentLifeFragment();
                break;
        }

        final Fragment finalFragment = fragment;
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.content_frame_main, finalFragment)
                        .commit();
            }
        }, 250);

    }

    public class DrawerListAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private int mSelectedItem;
        private int[] mDrawerDrawables = {R.drawable.ic_settings, R.drawable.ic_about};

        public DrawerListAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        public int getSelectedItem() {
            return mSelectedItem;
        }

        public void setSelectedItem(int i) {
            mSelectedItem = i;
        }

        @Override
        public int getCount() {
            return mDrawerItems.length;
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
        public View getView(int position, View convertView, ViewGroup parent) {

            if (position <= 3) {
                convertView = inflater.inflate(R.layout.layout_drawer_primary, null);
                TextView titlePrimary = (TextView) convertView.findViewById(R.id.textView_drawer_list_primary);
                titlePrimary.setText(mDrawerItems[position]);
                titlePrimary.setTextColor((mSelectedItem == position) ? Color.parseColor("#3f51b5") : Color.parseColor("#454545"));

                return convertView;
            } else {
                convertView = inflater.inflate(R.layout.layout_drawer_secondary, null);
                TextView titleSecondary = (TextView) convertView.findViewById(R.id.textView_drawer_list_secondary);
                titleSecondary.setText(mDrawerItems[position]);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_drawer_list_secondary);
                imageView.setImageResource(mDrawerDrawables[position - 4]);
                return convertView;
            }

        }
    }
}
