package com.prototype.ryersonapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {

    //private int previousFragment = 0;
    private int visibleFragment = 0;
    public ActionBarDrawerToggle mDrawerToggle;
    public DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private DrawerListAdapter mListAdapter;
    private String[] mDrawerItems = {"Campus Life", "Student Life", "Events", "Others", "Settings", "About Us"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");
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
                getActionBar().setTitle(mDrawerItems[visibleFragment]);
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
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
        mListAdapter.setSelectedItem(visibleFragment);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("MainActivity", "onPrepareOptionMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d("MainActivity", "onPostCreate");
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("MainActivity", "onConfigurationChanged");
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MainActivity", "onOptionsItemSelected");
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("MainActivity", "onItemClick " + i);
        if (visibleFragment != i) {
            mListAdapter.setSelectedItem(i);
            mListAdapter.notifyDataSetChanged();

            boolean isFragment = true;
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new CampusLifeFragment();
                    setVisibleFragment(i);
                    break;
                case 1:
                    fragment = new StudentLifeFragment();
                    setVisibleFragment(i);
                    break;
                case 2:
                    fragment = new EventsFragment();
                    setVisibleFragment(i);
                    break;
                case 4:
                    isFragment = false;
                    startActivity(new Intent("android.intent.action.RYERSONPREFERENCES"));
                    break;
                case 5:
                    isFragment = false;
                    startActivity(new Intent("android.intent.action.RYERSONABOUTUS"));
                    break;
            }

            if (isFragment) {
                final Fragment finalFragment = fragment;
                final int clickPosition = i;
                getActionBar().setTitle(mDrawerItems[i]);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.content_frame_main, finalFragment)
                                .commit();
                    }
                }, 250);
            }
        }
        mDrawerLayout.closeDrawer(mDrawerList);
        //previousFragment = i;
    }

    public void setVisibleFragment(int i) {
        visibleFragment = i;
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
