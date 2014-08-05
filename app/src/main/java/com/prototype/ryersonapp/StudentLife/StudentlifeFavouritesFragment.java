package com.prototype.ryersonapp.StudentLife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ryersonapp.R;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class StudentlifeFavouritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_studentlife_favourites, container, false);
    }
}
