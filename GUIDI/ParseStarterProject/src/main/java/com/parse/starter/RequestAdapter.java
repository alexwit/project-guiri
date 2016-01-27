package com.parse.starter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
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

//        super(context ,queryFactory);
//         Use the QueryFactory to construct a PQA that will only show
//         Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                Log.i("reqadap", "inside factory");
                ParseQuery query = new ParseQuery("DataBase");
                query.whereEqualTo("Guidematch", ParseUser.getCurrentUser().getObjectId());
//                query.whereEqualTo("Acceptuser", false);
//                query.whereEqualTo("Declineduser", false);
                query.orderByAscending("updatedAt");
                query.setLimit(5);
                return query;
            }
        });
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

//        TextView cityTextView = (TextView) v.findViewById(R.id.account_city_desciptrion);
//
//        TextView countryTextView = (TextView)v.findViewById(R.id.account_country_desciptrion);

        titleTextView.setText(object.getString("First_name"));
        ageTextView.setText(String.valueOf(object.getInt("Age")));
//        cityTextView.setText(String.valueOf(object.getString("City")));
//        countryTextView.setText(object.getString("Country"));
//
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("adapter", "im in the clicker ID= " + object.getObjectId().toString());
//                String itemId = object.getObjectId().toString();
//                Intent intent = new Intent(getContext(), GuideAccount.class);
//                intent.putExtra("ID", itemId);
//                getContext().startActivity(intent);
//            }
//        });

//        Button deleteBtn = (Button)v.findViewById(R.id.delete_btn);
//        Button addBtn = (Button)v.findViewById(R.id.add_btn);
////
//        deleteBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //do something
//                object.put("Declineduser", true);
//                object.saveInBackground();
//                notifyDataSetChanged();
//            }
//        });
//
//        addBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                TextView emailTourist = (TextView)v.findViewById(R.id.account_email_desciptrion);
//                object.put("Accepteduser", true);
//                emailTourist.setText(object.getString("Email"));
//                notifyDataSetChanged();
//            }
//        });

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



