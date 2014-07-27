package com.prototype.ryersonapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import java.util.Calendar;
import java.util.List;

public class ReminderAddNewFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private Button date, time, cancel, add;
    private EditText title, description;
    private Calendar calendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int tYear, tMonth, tDay, tHour, tMinute;
    private ReminderDatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_campuslife_reminders_addnewreminder, container, false);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().getActionBar().setHomeButtonEnabled(false);
        initialize();

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

        date.setOnClickListener(this);
        time.setOnClickListener(this);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        databaseHandler = new ReminderDatabaseHandler(getActivity());
    }

    public ReminderDatabaseHandler getReminderDatabaseHandler(){
        return databaseHandler;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_newreminder_date:

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

                break;
            case R.id.button_newreminder_time:

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

                break;
            case R.id.button_newreminder_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.button_newreminder_add:

                databaseHandler.addReminder(new Reminder(title.getText().toString(), description.getText().toString(),
                        Integer.toString(tDay), Integer.toString(tMonth),
                        Integer.toString(tYear), Integer.toString(tHour), Integer.toString(tMinute)));

                /**List<Reminder> reminders = databaseHandler.getAllReminders();
                for (int i = 0; i< databaseHandler.getRemindersCount(); i++) {
                    Reminder r = reminders.get(i);
                    String log = r.get_id() + " " + r.get_title() + " " + r.get_description();
                    Log.d("ADDNEWREMINDER", log);
                }**/

                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
