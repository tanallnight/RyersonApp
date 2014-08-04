package com.prototype.ryersonapp.Directory;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.prototype.ryersonapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tanmay on 2014-08-01.
 */
public class DirectoryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private View rootView;
    private Spinner spinner;
    private EditText searchText;
    private Button buttonText;
    private Document doc;
    private ProgressDialog progressDialog;
    private SearchResults results;
    private SearchByDepartment departmentResults;
    private List<String> nameList, titleList, locationList, extensionList, emailList;
    private DirectorySpinnerItems directorySpinnerItems;
    private String[] spinnerItems, spinnerItemValues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("Directory");
        rootView = inflater.inflate(R.layout.fragment_directory, container, false);

        initialize();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        spinner.setSelection(0);
        searchText.setText("");
    }

    public void initialize() {
        directorySpinnerItems = new DirectorySpinnerItems();
        spinnerItems = directorySpinnerItems.getSpinnerItems();
        spinnerItemValues = directorySpinnerItems.getSpinnerItemValues();

        nameList = new LinkedList<String>();
        titleList = new LinkedList<String>();
        locationList = new LinkedList<String>();
        extensionList = new LinkedList<String>();
        emailList = new LinkedList<String>();

        spinner = (Spinner) rootView.findViewById(R.id.spinner_directory);
        searchText = (EditText) rootView.findViewById(R.id.edittext_directory_search);
        buttonText = (Button) rootView.findViewById(R.id.button_directory_searchtext);
        buttonText.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        String inputText = searchText.getText().toString();
        if (inputText.length() >= 3) {
            if (isNetworkAvailable()) {
                results = new SearchResults(inputText);
                results.execute();
            } else {
                Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Search must contain at least 3 characters", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i != 0) {
            if (isNetworkAvailable()) {
                departmentResults = new SearchByDepartment(spinnerItemValues[i]);
                departmentResults.execute();
            } else {
                Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class SearchByDepartment extends AsyncTask<Void, Void, Void> {

        private String text;

        public SearchByDepartment(String spinnerItemValue) {
            text = spinnerItemValue;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            nameList.clear();
            titleList.clear();
            locationList.clear();
            extensionList.clear();
            emailList.clear();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://m.ryerson.ca/core_apps/directory/index.cfm")
                        .data("department", text)
                        .data("subDept", "Find")
                        .post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Elements results = doc.select("div[class=staffMember]");
            int x = results.size();
            for (int i = 1; i < results.size(); i++) {
                Element oneResult = results.get(i);
                int j = 0;
                while (j < 5) {
                    nameList.add(oneResult.child(j).text());
                    j++;
                    titleList.add(oneResult.child(j).text());
                    j++;
                    locationList.add(oneResult.child(j).text());
                    j++;
                    extensionList.add(oneResult.child(j).text());
                    j++;
                    emailList.add(oneResult.child(j).text());
                    j++;
                }
            }
            progressDialog.dismiss();

            String[] namesArray = nameList.toArray(new String[0]);
            String[] titlesArray = titleList.toArray(new String[0]);
            String[] locationsArray = locationList.toArray(new String[0]);
            String[] extensionsArray = extensionList.toArray(new String[0]);
            String[] emailsArray = emailList.toArray(new String[0]);

            Fragment resultFragment = new DirectoryResultsFragment();
            Bundle args = new Bundle();
            args.putStringArray("ARRAY_NAME", namesArray);
            args.putStringArray("ARRAY_TITLE", titlesArray);
            args.putStringArray("ARRAY_LOCATION", locationsArray);
            args.putStringArray("ARRAY_EXTENSION", extensionsArray);
            args.putStringArray("ARRAY_EMAIL", emailsArray);
            resultFragment.setArguments(args);

            getActivity().getFragmentManager().beginTransaction()
                    //.setCustomAnimations(R.anim.slide_up_enter, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_down_exit)
                    .replace(R.id.content_frame_directory, resultFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    public class SearchResults extends AsyncTask<Void, Void, Void> {

        private String text;

        public SearchResults(String searchText) {
            text = searchText;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            nameList.clear();
            titleList.clear();
            locationList.clear();
            extensionList.clear();
            emailList.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://m.ryerson.ca/core_apps/directory/index.cfm")
                        .data("searchInput", text)
                        .data("subName", "Find")
                        .post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Elements results = doc.select("div[class=staffMember]");
            int x = results.size();
            for (int i = 0; i < results.size(); i++) {
                Element oneResult = results.get(i);
                int j = 0;
                while (j < 5) {
                    nameList.add(oneResult.child(j).text());
                    j++;
                    titleList.add(oneResult.child(j).text());
                    j++;
                    locationList.add(oneResult.child(j).text());
                    j++;
                    extensionList.add(oneResult.child(j).text());
                    j++;
                    emailList.add(oneResult.child(j).text());
                    j++;
                }
            }
            progressDialog.dismiss();

            String[] namesArray = nameList.toArray(new String[0]);
            String[] titlesArray = titleList.toArray(new String[0]);
            String[] locationsArray = locationList.toArray(new String[0]);
            String[] extensionsArray = extensionList.toArray(new String[0]);
            String[] emailsArray = emailList.toArray(new String[0]);

            Fragment resultFragment = new DirectoryResultsFragment();
            Bundle args = new Bundle();
            args.putStringArray("ARRAY_NAME", namesArray);
            args.putStringArray("ARRAY_TITLE", titlesArray);
            args.putStringArray("ARRAY_LOCATION", locationsArray);
            args.putStringArray("ARRAY_EXTENSION", extensionsArray);
            args.putStringArray("ARRAY_EMAIL", emailsArray);
            resultFragment.setArguments(args);

            getActivity().getFragmentManager().beginTransaction()
                    //.setCustomAnimations(R.anim.slide_up_enter, R.anim.alpha_out, R.anim.alpha_in, R.anim.slide_down_exit)
                    .replace(R.id.content_frame_directory, resultFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
