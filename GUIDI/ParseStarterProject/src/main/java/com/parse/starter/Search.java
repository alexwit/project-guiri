package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Collections;
import java.util.List;

/**
 * Created by Papi lexus on 13-1-2016.
 */
public class Search {

    public ParseQueryAdapter.QueryFactory searchUsers(final String city) {

        ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
                ParseQuery query = ParseQuery.getQuery("_User");
                query.whereEqualTo("City", city);
                query.whereNotEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
                Log.i("search", "in search city is: " + city);
                return query;
            }
        };

        return queryFactory;

    }

    public List<ParseQuery> searchRequests() {

//        List<ParseObject> AL = new List<ParseObject>() {
        final List<ParseQuery> queryList = Collections.emptyList();
//            public ParseQuery create() {
        ParseQuery query = ParseQuery.getQuery("DataBase");
        Log.i("Seearch adapt", " current user" + ParseUser.getCurrentUser().toString());
        query.whereEqualTo("GuideMatchId", ParseUser.getCurrentUser().toString());
        query.whereNotEqualTo("Declineduser", true);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        String objectId = object.getObjectId();
                        ParseQuery nextQuery = ParseQuery.getQuery("_User");
                        nextQuery.whereEqualTo("objectId", objectId);
                        queryList.add(nextQuery);
                    }

                }

            }

        });

        return queryList;
    }
}
