package com.prototype.ryersonapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Shahar on 2014-07-31.
 */
public class ExploreActivity extends Activity {

    private String Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        ListView ExploreViewListItems = (ListView)findViewById(R.id.ListExploreView);
        ArrayAdapter<String> ExploreListAdapter = new ListAdapter(this, R.layout.activity_explore_card_epandable);

        Title= getIntent().getStringExtra("Name");
        setTitle(Title);


        if(Title.equals("Coffee Deals"))
        {
            ExploreListAdapter.add("Second Cup");
            ExploreListAdapter.add("Tim Hortons");//sending store name

        }
        else if(Title.equals("Shopping Deals"))
        {
            ExploreListAdapter.add("Shoppers Drug Mart");
        }
        else if(Title.equals("Eating Deals"))
        {

        }
        else if(Title.equals("Drink Deals"))
        {

        }




        ExploreViewListItems.setAdapter(ExploreListAdapter);

        ExploreViewListItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                View ExploreExpand = view.findViewById(R.id.EpandedInfo);


                ExpandAnimation expandAni = new ExpandAnimation(ExploreExpand, 500);
                ExploreExpand.startAnimation(expandAni);
            }
        });
    }


    class ListAdapter extends ArrayAdapter<String> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.activity_explore_card_epandable, null);
            }

            TextView StoreName = (TextView)convertView.findViewById(R.id.StoreName);
            ImageView StoreLogo =(ImageView)convertView.findViewById(R.id.StoreLogo);

            StoreName.setText(getItem(position));//set the text

            if(Title.equals("Coffee Deals"))
            {

                if(StoreName.getText().equals("Tim Hortons"))
                    StoreLogo.setImageResource(R.drawable.timhortons);//set the image resource
                else if(StoreName.getText().equals("Second Cup"))
                    StoreLogo.setImageResource(R.drawable.secondcup);
            }
            else if(Title.equals("Shopping Deals"))
            {
                if(StoreName.getText().equals("Shoppers Drug Mart"));
                StoreLogo.setImageResource(R.drawable.shopersdrugmart);
            }
            else if(Title.equals("Eating Deals"))
            {

            }
            else if(Title.equals("Drinking Deals"))
            {

            }



            View toolbar = convertView.findViewById(R.id.EpandedInfo);
            ((LinearLayout.LayoutParams) toolbar.getLayoutParams()).bottomMargin = -50;
            toolbar.setVisibility(View.GONE);

            return convertView;
        }
    }
}