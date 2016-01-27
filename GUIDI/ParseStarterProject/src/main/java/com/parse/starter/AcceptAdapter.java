package com.parse.starter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Papi lexus on 27-1-2016.
 * Alex Wittebrood student# 10288880
 */
public class AcceptAdapter extends ParseQueryAdapter<ParseObject> {

// Custom adapter
    public AcceptAdapter(Context context, ParseQueryAdapter.QueryFactory queryFactory) {

        // Adapter gets all account information from the QueryFactory
        super(context, queryFactory);

    }

    // Specifications of each item in the listview
    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.match_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add the the name  of the view
        TextView nameTextView = (TextView) v.findViewById(R.id.account_name_description);
        TextView ageTextView = (TextView) v.findViewById(R.id.account_age_description);
        TextView cityTextView = (TextView) v.findViewById(R.id.account_city_description);
        TextView countryTextView = (TextView) v.findViewById(R.id.account_country_description);
        TextView emailTextView = (TextView) v.findViewById(R.id.account_email_description);

        // sets the guide information
        nameTextView.setText(object.getString("GuideName") + " " + object.get("GuideSurname"));
        ageTextView.setText(String.valueOf(object.getInt("GuideAge")));
        cityTextView.setText(String.valueOf(object.getString("GuideCity")));
        countryTextView.setText(object.getString("GuideCountry"));
        emailTextView.setText(object.getString("EmailGuide"));

        return v;
    }
}