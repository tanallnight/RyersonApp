package com.informeapps.informeryerson.CampusMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.informeapps.informeryerson.R;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class CampusMapActivity extends Activity {

    private String mapsUrl = "https://m.ryerson.ca/core_apps/map/beta/";
    private WebView webView;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusmap);

        webView = (WebView) findViewById(R.id.webview_campusmap);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar_campusmap);
        textView = (TextView) findViewById(R.id.textview_campusmap_progress);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        final Activity activity = this;

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress != 100) {
                    textView.setText(progress + "%");
                    //progressBar.setProgress(progress);
                } else {
                    textView.setVisibility(View.GONE);
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(mapsUrl)) {
                    view.loadUrl(url);
                }

                return true;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(mapsUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.campusmap_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_campusmap_refresh:
                webView.reload();
                textView.setText("0%");
                textView.setVisibility(View.VISIBLE);
                //progressBar.setVisibility(View.VISIBLE);
                //progressBar.setProgress(0);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
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
    }*/
}
