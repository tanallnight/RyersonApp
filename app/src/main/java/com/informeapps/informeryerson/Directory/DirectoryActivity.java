package com.informeapps.informeryerson.Directory;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.informeapps.informeryerson.R;

/**
 * Created by Tanmay on 2014-08-01.
 */
public class DirectoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame_directory, new DirectoryFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
