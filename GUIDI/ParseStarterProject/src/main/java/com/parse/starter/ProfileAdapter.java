package com.parse.starter;

import android.content.Context;
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

public class ProfileAdapter extends ParseQueryAdapter {




    private List<ParseUser> mUserList;

    public ProfileAdapter(Context context, List<ParseUser> list) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, list);
//            public ParseQuery create() {
//
//                ParseQuery<ParseUser> query = ParseUser.getQuery();
//                query.whereEqualTo("City", "Amsterdam");
//                query.setLimit(5);
//                query.orderByDescending("username");
//                return query;
            }




    // Customize the layout by overriding getItemView

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.account_rows, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
//        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
//        ParseFile imageFile = object.getParseFile("image");
//        if (imageFile != null) {
//            todoImage.setParseFile(imageFile);
//            todoImage.loadInBackground();
//        }

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.account_description);
        titleTextView.setText(object.getString("First_name"));

//        // Add a reminder of how long this item has been outstanding
//        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
//        timestampView.setText(object.getCreatedAt().toString());

        return v;
    }


//    private List<ParseUser> mUserList;
//
//    public ProfileAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
//        super(context, queryFactory);
//    }
//
//    @Override
//    public View getItemView(final ParseUser user, View v, ViewGroup parent) {
//    // build your views
//    }
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



