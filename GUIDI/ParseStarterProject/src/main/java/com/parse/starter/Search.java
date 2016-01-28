package com.parse.starter;

import android.util.Log;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Alex Wittebrood # 10288880
 * Created by Papi lexus on 13-1-2016.
 */
public class Search {

    // Searches for users from a specific city
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

    // Searches for the users which Requested the guide
    public ParseQueryAdapter.QueryFactory searchRequests(){
        ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
                Log.i("reqadap", "inside factory");
                ParseQuery query = ParseQuery.getQuery("DataBase");
                Log.i(" request guide id ", "user " + ParseUser.getCurrentUser().getObjectId());
                query.whereEqualTo("GuideMatchId", ParseUser.getCurrentUser().getObjectId());
                query.whereEqualTo("Acceptuser", false);
                query.whereEqualTo("Declineduser", false);
                query.orderByAscending("updatedAt");

//                query.setLimit(5);
                return query;
            }
        };

        return queryFactory;
    }


    // Searches the Guides which the User request accepted
    public ParseQueryAdapter.QueryFactory searchAccepted(){
        ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
                Log.i("reqadap", "inside factory");
                ParseQuery query = ParseQuery.getQuery("DataBase");
                Log.i(" request guide id ", "user " + ParseUser.getCurrentUser().getObjectId());
                query.whereEqualTo("UserReqId", ParseUser.getCurrentUser().getObjectId());
                query.whereEqualTo("Acceptuser", true);
                query.whereEqualTo("Declineduser", false);
                query.orderByAscending("updatedAt");

//                query.setLimit(5);
                return query;
            }
        };

        return queryFactory;
    }
}
