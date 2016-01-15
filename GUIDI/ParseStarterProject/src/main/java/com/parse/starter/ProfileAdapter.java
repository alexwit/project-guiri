package com.parse.starter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Papi lexus on 14-1-2016.
 */

// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class ProfileAdapter extends ParseQueryAdapter {






    // Customize the layout by overriding getItemView


    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.urgent_item, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("image");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(object.getString("title"));

        // Add a reminder of how long this item has been outstanding
        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
        timestampView.setText(object.getCreatedAt().toString());

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



