package com.prototype.ryersonapp.Directory;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.prototype.ryersonapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tanmay on 2014-08-04.
 */
public class SearchByKeyword extends AsyncTask<Void, Void, Void> {

    private String text;
    private Document doc;
    private Activity activity;
    private ProgressDialog progressDialog;
    private List<String> nameList, titleList, locationList, extensionList, emailList;

    public SearchByKeyword(String searchText, Activity activity) {
        text = searchText;
        this.activity = activity;

        nameList = new LinkedList<String>();
        titleList = new LinkedList<String>();
        locationList = new LinkedList<String>();
        extensionList = new LinkedList<String>();
        emailList = new LinkedList<String>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
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

        activity.getFragmentManager().beginTransaction()
                .replace(R.id.content_frame_directory, resultFragment)
                .addToBackStack(null)
                .commit();
    }
}
