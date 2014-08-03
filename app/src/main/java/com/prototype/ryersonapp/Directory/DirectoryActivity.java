package com.prototype.ryersonapp.Directory;

import android.app.Activity;
import android.os.Bundle;

import com.prototype.ryersonapp.R;

/**
 * Created by Tanmay on 2014-08-01.
 */
public class DirectoryActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame_directory, new DirectoryFragment())
                .commit();
    }
}
