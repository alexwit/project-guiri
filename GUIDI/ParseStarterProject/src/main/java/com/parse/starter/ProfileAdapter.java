package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Papi lexus on 14-1-2016.
 */

// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class ProfileAdapter extends ParseQueryAdapter<ParseObject> {


    private List<ParseUser> mUserList;

    public ProfileAdapter(Context context, QueryFactory<ParseObject> queryFactory) {

        super(context, queryFactory);

    }

    ArrayList<String> objectID = new ArrayList<>();

    // Customize the layout by overriding getItemView

    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_item, null);
            Log.i("adapter", "inside if no view");
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.account_name_description);
        Log.i("adapter", "first name object " + object.getString("First_name"));
        titleTextView.setText(object.getString("First_name"));
        TextView ageTextView = (TextView) v.findViewById(R.id.account_age_description);
        ageTextView.setText(String.valueOf(object.getInt("Age")));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("adapter", "im in the clicker ID= " + object.getObjectId().toString());
                String itemId = object.getObjectId().toString();
                Intent intent = new Intent(getContext(), GuideAccount.class);
                intent.putExtra("ID", itemId);
                getContext().startActivity(intent);
            }
        });

        return v;
    }

}



