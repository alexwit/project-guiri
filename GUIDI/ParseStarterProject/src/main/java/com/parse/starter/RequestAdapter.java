package com.parse.starter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Papi lexus on 14-1-2016.
 */

// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class RequestAdapter extends ParseQueryAdapter<ParseObject> {

    OnDataChanged callBack;

    public RequestAdapter(Context context, ParseQueryAdapter.QueryFactory queryFactory,  OnDataChanged callBack) {

//        super(context ,queryFactory);
//         Use the QueryFactory to construct a PQA that will only show
//         Todos marked as high-pri
        super(context, queryFactory);
        this.callBack = callBack;

    }


    // Customize the layout by overriding getItemView

    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        Log.i("reqada", "inside itemview");
        if (v == null) {
            v = View.inflate(getContext(), R.layout.guide_list_item, null);
            Log.i("reqadapter", "inside if no view");
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.account_name_description);

        TextView ageTextView = (TextView) v.findViewById(R.id.account_age_description);
//
        TextView cityTextView = (TextView) v.findViewById(R.id.account_city_description);
//
        TextView countryTextView = (TextView)v.findViewById(R.id.account_country_description);

        titleTextView.setText(object.getString("TouristName") + " " + object.get("TouristSurname"));
        ageTextView.setText(String.valueOf(object.getInt("TouristAge")));
        cityTextView.setText(String.valueOf(object.getString("TouristCity")));
        countryTextView.setText(object.getString("TouristCountry"));


        Button deleteBtn = (Button)v.findViewById(R.id.btn_delete);
        Button addBtn = (Button)v.findViewById(R.id.btn_add);
//
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                object.put("Declineduser", true);
                object.saveInBackground();
                callBack.DataChanged();

                Toast.makeText(getContext(), "User Declined", Toast.LENGTH_SHORT).show();

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                TextView emailTourist = (TextView)v.findViewById(R.id.account_email_description);
                object.put("Acceptuser", true);
//                emailTourist.setText(object.getString("Email"));
                Toast.makeText(getContext(), "User Accepted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                object.saveInBackground();
                callBack.DataChanged();
            }
        });

        return v;
    }

}



