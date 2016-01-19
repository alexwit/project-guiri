package com.parse.starter;

import android.app.ListActivity;
import android.util.Log;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Papi lexus on 13-1-2016.
 */
public class Search extends ListActivity {

    public ParseQueryAdapter.QueryFactory searchUsers(final String city) {

        ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
                ParseQuery query = ParseQuery.getQuery("_User");
                query.whereEqualTo("City", city);
                Log.i("search", "in search city is: " + city);
                return query;
            }
        };

        return queryFactory;

    }

}
