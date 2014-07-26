package com.prototype.ryersonapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class RemindersFragments extends Fragment {

    private static FloatingActionButton floatingActionButton;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife_reminders, container, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = new FloatingActionButton.Builder(getActivity())
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_add))
                .withButtonColor(Color.parseColor("#e91e63"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up_enter);
        slideUp.setInterpolator(getActivity(), android.R.anim.overshoot_interpolator);
        floatingActionButton.setAnimation(slideUp);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_exit);
                slideDown.setInterpolator(getActivity(), android.R.anim.anticipate_interpolator);
                floatingActionButton.setAnimation(slideDown);
                floatingActionButton.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_up_enter, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_down_exit)
                        .replace(R.id.content_frame_reminders, new newReminderFragment(), "NEW_REMINDER_FRAGMENT")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }

    public static class newReminderFragment extends Fragment {

        private View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_campuslife_reminders_addnewreminder, container, false);
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
            return rootView;
        }
    }
}
