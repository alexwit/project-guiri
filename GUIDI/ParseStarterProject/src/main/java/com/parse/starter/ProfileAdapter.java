package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;

/**
 * Created by Papi lexus on 14-1-2016.
 */
// Alex Wittebrood # 10288880
// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class ProfileAdapter extends ParseQueryAdapter<ParseObject> {


    public ProfileAdapter(Context context, QueryFactory<ParseObject> queryFactory) {

        super(context, queryFactory);

    }

    ArrayList<String> objectID = new ArrayList<>();

    // Customizes the layout and functionallity of the specific items
    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.account_name_description);
        titleTextView.setText(object.getString("First_name") + " " + object.getString("Surname"));
        TextView ageTextView = (TextView) v.findViewById(R.id.account_age_description);
        ageTextView.setText(String.valueOf(object.getInt("Age")));

        // Sends the objectId of the found user to class GuideAccount
        // So in the next activity the correct full account information is shown
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = object.getObjectId().toString();
                Intent intent = new Intent(getContext(), GuideAccount.class);
                intent.putExtra("ID", itemId);
                getContext().startActivity(intent);
            }
        });

        return v;
    }

}



