package com.informeapps.informeryerson;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Tanmay on 2014-08-07.
 */
public class ScheduleActivity extends FragmentActivity {

    public Calendar[] Day;
    public String[] Days;
    public String[] MonthDays;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myschedule);

        Day = new Calendar[180];
        Days = new String[180];
        MonthDays = new String[180];

        for (int z = 0; z < 180; z++) {
            Day[z] = shiftedCalender(Calendar.getInstance(), z);
            Days[z] = shiftDate(Calendar.getInstance(), z);
            MonthDays[z] = whatMonth(Day[z]);
        }

        final ScheduleListDateAdapter adapter = new ScheduleListDateAdapter(this, Days, MonthDays);
        listView = (ListView) findViewById(R.id.listview_myschedule_date);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), "Clicked this " + i, Toast.LENGTH_SHORT).show();
                onItemSelection(i);
            }

        });
        onItemSelection(0);

    }


    public Calendar shiftedCalender(Calendar c, int Shift) {
        Calendar calendar = c;
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        calendar.add(Calendar.DAY_OF_YEAR, Shift);
        return calendar;
    }

    public String shiftDate(Calendar c, int Shift) {
        Calendar calendar = c;
        calendar.add(Calendar.DAY_OF_MONTH, Shift);
        return (new SimpleDateFormat("EEE").format(calendar.getTime()));
    }

    public String whatMonth(Calendar c) {
        return (new SimpleDateFormat("MMM, d").format(c.getTime()));


    }


    public void onItemSelection(int Pos) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", Pos);
        ScheduleDetailFragment fragInfo = new ScheduleDetailFragment(Day, this);
        fragInfo.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.content_frame_myschedule, fragInfo).commit();

    }
}
