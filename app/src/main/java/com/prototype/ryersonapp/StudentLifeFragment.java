package com.prototype.ryersonapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

public class StudentLifeFragment extends Fragment{

    private View rootView;
    private ViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;
    private int pos;
    private TextView coffee, eat, drink, shop;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_studentlife, container, false);

        initialize();

        viewPager = (ViewPager) rootView.findViewById(R.id.pager_studentlife_offers_banner);
        viewPager.setAdapter(new StudentLifeSlideAdapter(getActivity().getSupportFragmentManager()));

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() == 4) {
                    pos = 0;
                }
                viewPager.setCurrentItem(pos++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1, 5000);

        return rootView;
    }

    private void initialize(){

    }

    public static class StudentLifeBannerFragment extends Fragment {

        private int position;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Bundle b = getArguments();
            this.position = b.getInt("POSITION");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.layout_studentlife_banner, container, false);

            ImageView image = (ImageView) rootView.findViewById(R.id.imageview_studentlife_banner);

            switch (position) {
                case 0:
                    Picasso.with(getActivity()).load(R.raw.campuslife_header).into(image);
                    break;
                case 1:
                    Picasso.with(getActivity()).load(R.raw.campuslife_header).into(image);
                    break;
                case 2:
                    Picasso.with(getActivity()).load(R.raw.campuslife_header).into(image);
                    break;
                case 3:
                    Picasso.with(getActivity()).load(R.raw.campuslife_header).into(image);
                    break;
                case 4:
                    Picasso.with(getActivity()).load(R.raw.campuslife_header).into(image);
                    break;
            }

            return rootView;
        }
    }

    public class StudentLifeSlideAdapter extends FragmentStatePagerAdapter {

        public StudentLifeSlideAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = new StudentLifeBannerFragment();
            Bundle args = new Bundle();
            args.putInt("POSITION", i);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
