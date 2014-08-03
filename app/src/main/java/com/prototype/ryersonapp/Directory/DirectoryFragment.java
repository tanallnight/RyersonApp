package com.prototype.ryersonapp.Directory;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prototype.ryersonapp.R;

/**
 * Created by Tanmay on 2014-08-01.
 */
public class DirectoryFragment extends Fragment{

    private View rootView;
    private Spinner spinner;
    private String[] spinnerItems = {"Mechanical Engineering", "Stuff", "More Stuff", "Even More Stuff"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_directory, container, false);
        spinner = (Spinner) rootView.findViewById(R.id.spinner_directory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return rootView;
    }
}
