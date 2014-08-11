package com.informeapps.informeryerson;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class CampusMapActivity extends FragmentActivity {

    //private WebView webView;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);
        setUpMapIfNeeded();
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
        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ryerson, 16));
        Polygon MON = googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(43.659634, -79.378301), new LatLng(43.659791, -79.37836), new LatLng(43.659816, -79.378362),
                        new LatLng(43.659906, -79.377967), new LatLng(43.659759, -79.377907), new LatLng(43.659728, -79.377906),
                        new LatLng(43.659712, -79.377985), new LatLng(43.659716, -79.37799)));
        MON.setStrokeColor(Color.GRAY);
        MON.setFillColor(Color.LTGRAY);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(43.65978, -79.378136)).title("MON"));
    }
}
