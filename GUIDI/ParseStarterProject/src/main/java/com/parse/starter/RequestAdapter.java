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

import java.util.List;

/**
 * Created by Papi lexus on 14-1-2016.
 */

// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class RequestAdapter extends ParseQueryAdapter<ParseObject> {


    private List<ParseUser> mUserList;


    public RequestAdapter(Context context, ParseQueryAdapter.QueryFactory queryFactory) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri

        super(context, queryFactory);
        Log.i("reqadap", "inside it ");


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
        Log.i("reqadapter", "first name object " + object.getString("First_name"));
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

//
//    public void setData(List<ParseUser> userList) {
//        mUserList = userList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return mUserList.size();
//    }
}



