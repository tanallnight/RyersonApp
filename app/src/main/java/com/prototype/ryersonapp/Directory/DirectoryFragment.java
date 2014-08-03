package com.prototype.ryersonapp.Directory;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class DirectoryFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private Spinner spinner;
    private EditText searchText;
    private Button buttonText;
    private Document doc;
    private ProgressDialog progressDialog;
    private SearchResults results;
    private List<String> nameList, titleList, locationList, extensionList, emailList;
    private String[] spinnerItems = {"Mechanical Engineering", "Stuff", "More Stuff", "Even More Stuff"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_directory, container, false);

        initialize();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return rootView;
    }

    public void initialize() {
        nameList = new LinkedList<String>();
        titleList = new LinkedList<String>();
        locationList = new LinkedList<String>();
        extensionList = new LinkedList<String>();
        emailList = new LinkedList<String>();

        spinner = (Spinner) rootView.findViewById(R.id.spinner_directory);
        searchText = (EditText) rootView.findViewById(R.id.edittext_directory_search);
        buttonText = (Button) rootView.findViewById(R.id.button_directory_searchtext);
        buttonText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String inputText = searchText.getText().toString();
        if (inputText.length() >= 3) {
            results = new SearchResults(inputText);
            results.execute();
        } else {
            Toast.makeText(getActivity(), "Search must contain at least 3 characters", Toast.LENGTH_SHORT).show();
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
        }
    }
}
