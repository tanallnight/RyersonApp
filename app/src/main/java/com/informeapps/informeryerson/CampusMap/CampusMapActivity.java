package com.informeapps.informeryerson.CampusMap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.informeapps.informeryerson.R;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class CampusMapActivity extends FragmentActivity {

    private GoogleMap googleMap;
    private AutoCompleteTextView autoCompleteTextView;
    private String[] locationsArray;
    private double[] locationLatArray, locationLongArray;
    private boolean closeActivity = true;
    private Marker building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);

        setUpMapIfNeeded();

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocompletetextview_campusmap);
        CampusMapDataStorage dataStorage = new CampusMapDataStorage();
        locationsArray = dataStorage.getLocationsArray();
        locationLatArray = dataStorage.getLocationLatArray();
        locationLongArray = dataStorage.getLocationLongArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, locationsArray);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                building = googleMap.addMarker(new MarkerOptions().position(new LatLng(locationLatArray[i], locationLongArray[i])).title(locationsArray[i]));
                closeActivity = false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (closeActivity) {
            super.onBackPressed();
            return;
        } else {
            building.remove();
            closeActivity = true;
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (googleMap != null) {
            return;
        }

        googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        if (googleMap == null) {
            return;
        }

        LatLng ryerson = new LatLng(43.657929, -79.378935);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ryerson, 16));
        Polygon MON = googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(43.659634, -79.378301), new LatLng(43.659791, -79.37836), new LatLng(43.659816, -79.378362),
                        new LatLng(43.659906, -79.377967), new LatLng(43.659759, -79.377907), new LatLng(43.659728, -79.377906),
                        new LatLng(43.659712, -79.377985), new LatLng(43.659716, -79.37799)));
        MON.setStrokeColor(Color.GRAY);
        MON.setStrokeWidth(3);
        MON.setFillColor(Color.LTGRAY);
    }
}
