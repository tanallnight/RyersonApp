package com.prototype.ryersonapp.StudentLife;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prototype.ryersonapp.R;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class StudentlifeFavouritesFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_studentlife_favourites, container, false);
        return rootView;
    }
}
