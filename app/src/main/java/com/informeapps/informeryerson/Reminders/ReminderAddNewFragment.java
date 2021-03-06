package com.informeapps.informeryerson.Reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.informeapps.informeryerson.R;

import java.util.Calendar;

public class ReminderAddNewFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private Button date, time, cancel, add;
    private EditText title, description;
    private Calendar calendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int tYear, tMonth, tDay, tHour, tMinute;
    private ReminderDatabaseHandler databaseHandler;
    private ImageView done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife_reminders_addnewreminder, container, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);
        initialize();

        done.setVisibility(View.GONE);
        return rootView;
    }

    private void initialize() {

        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);

        date = (Button) rootView.findViewById(R.id.button_newreminder_date);
        time = (Button) rootView.findViewById(R.id.button_newreminder_time);
        cancel = (Button) rootView.findViewById(R.id.button_newreminder_cancel);
        add = (Button) rootView.findViewById(R.id.button_newreminder_add);
        title = (EditText) rootView.findViewById(R.id.edittext_newreminder_title);
        description = (EditText) rootView.findViewById(R.id.edittext_newreminder_description);
        done = (ImageView) rootView.findViewById(R.id.imageview_newreminder_done);

        date.setOnClickListener(this);
        time.setOnClickListener(this);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        databaseHandler = new ReminderDatabaseHandler(getActivity());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_newreminder_date:
                datePickerPressed();
                break;
            case R.id.button_newreminder_time:
                timePickerPressed();
                break;
            case R.id.button_newreminder_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.button_newreminder_add:
                addButtonPressed();
                break;
        }
    }

    private void addButtonPressed() {

        if (!title.getText().toString().equals("")) {
            databaseHandler.addReminder(new Reminder(title.getText().toString(), description.getText().toString(),
                    tDay, tMonth, tYear, tHour, tMinute));
            add.setClickable(false);
            processNotification();
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up_rotate);
            animation.setInterpolator(getActivity(), android.R.anim.overshoot_interpolator);
            done.setAnimation(animation);
            done.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }, 700);
        } else {
            Toast.makeText(getActivity(), "Title field cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void timePickerPressed() {
        RadialTimePickerDialog radialTimePickerDialog = RadialTimePickerDialog
                .newInstance(new RadialTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
                        if (i < 12) {
                            if (i == 0)
                                i = 12;
                            time.setText("[ " + i + ":" + i2 + " AM ]");
                        } else {
                            time.setText("[ " + (i - 12) + ":" + i2 + " PM ]");
                        }
                        tHour = i;
                        tMinute = i2;
                    }
                }, mHour, mMinute, false);
        radialTimePickerDialog.show(getActivity().getSupportFragmentManager(), "TIME_PICKER");
    }

    private void datePickerPressed() {
        CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
                .newInstance(new CalendarDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {
                        date.setText("[ " + i3 + "/" + i2 + "/" + i + " ]");
                        tDay = i3;
                        tMonth = i2;
                        tYear = i;
                    }
                }, mYear, mMonth, mDay);
        calendarDatePickerDialog.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");

    }

    private void processNotification() {
        Calendar notificationDate = Calendar.getInstance();
        notificationDate.setTimeInMillis(System.currentTimeMillis());
        notificationDate.set(tYear, tMonth, tDay, tHour, tMinute);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(getActivity(), RemindersReceiver.class);
        Bundle args = new Bundle();
        args.putString("KEY_TITLE", title.getText().toString());
        args.putString("KEY_DESCRIPTION", description.getText().toString());
        notificationIntent.putExtra("TITLE_DESCRIPTION", args);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, notificationDate.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, notificationDate.getTimeInMillis(), pendingIntent);
        }
    }
}
