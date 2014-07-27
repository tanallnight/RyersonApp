package com.prototype.ryersonapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RemindersFragments extends Fragment {

    private static FloatingActionButton floatingActionButton;
    private View rootView;
    private ListView listView;
    private ReminderDatabaseHandler databaseHandler;
    private ReminderListAdapter adapter;
    private List<Reminder> reminderList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife_reminders, container, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHandler = new ReminderDatabaseHandler(getActivity());
        reminderList = databaseHandler.getAllReminders();

        listView = (ListView) rootView.findViewById(R.id.listview_remiders);
        adapter = new ReminderListAdapter();
        listView.setAdapter(adapter);

        initializeFloatingActionButton();

        return rootView;
    }

    private void initializeFloatingActionButton() {
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
                //Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                //vibrator.vibrate((long) 5);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_exit);
                slideDown.setInterpolator(getActivity(), android.R.anim.anticipate_interpolator);
                floatingActionButton.setAnimation(slideDown);
                floatingActionButton.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_up_enter, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_down_exit)
                        .replace(R.id.content_frame_reminders, new ReminderAddNewFragment(), "NEW_REMINDER_FRAGMENT")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private class ReminderListAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public ReminderListAdapter() {
            inflater = getActivity().getLayoutInflater();
        }

        @Override
        public int getCount() {
            Log.d("GETCOUNT", "" + databaseHandler.getRemindersCount());
            return databaseHandler.getRemindersCount();
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
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflater.inflate(R.layout.layout_list_reminders, null);

            TextView day = (TextView) view.findViewById(R.id.textview_reminder_list_day);
            TextView month = (TextView) view.findViewById(R.id.textview_reminder_list_month);
            TextView time = (TextView) view.findViewById(R.id.textview_reminder_list_time);
            TextView title = (TextView) view.findViewById(R.id.textview_reminder_list_title);
            TextView description = (TextView) view.findViewById(R.id.textview_reminder_list_description);

            List<Reminder> reminders = databaseHandler.getAllReminders();
            Reminder reminder = reminders.get(i);

            String mDay = reminder.get_day();
            String mMonth = reminder.get_month();
            String mHour = reminder.get_hour();
            String mMinutes = reminder.get_minute();
            String mTitle = reminder.get_title();
            String mDescription = reminder.get_description();
            String mTime = null;
            Log.d(mMonth, mMonth);
            mMonth = getMonth(mMonth);

            day.setText(mDay);
            month.setText(mMonth);
            time.setText(mTime);
            title.setText(mTitle);
            description.setText(mDescription);

            return view;
        }

        private String getMonth(String inputMonth) {
            String month = null;
            if (inputMonth == "1")
                month = "JAN";
            else if (inputMonth == "2")
                month = "FEB";
            else if (inputMonth == "3")
                month = "MAR";
            else if (inputMonth == "4")
                month = "APR";
            else if (inputMonth == "5")
                month = "MAY";
            else if (inputMonth == "6")
                month = "JUN";
            else if (inputMonth == "7")
                month = "JUL";
            else if (inputMonth == "8")
                month = "AUG";
            else if (inputMonth == "9")
                month = "SEP";
            else if (inputMonth == "10")
                month = "OCT";
            else if (inputMonth == "11")
                month = "NOV";
            else if (inputMonth == "12")
                month = "DEC";

            return month;
        }
    }
}
