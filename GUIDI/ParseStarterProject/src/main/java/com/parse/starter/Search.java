package com.parse.starter;

import android.app.ListActivity;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

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

    public ParseQueryAdapter.QueryFactory searchRequests() {

        ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
                ParseQuery query = ParseQuery.getQuery("DataBase");
                query.include("Userrequest");
                query.whereEqualTo("Guidematch", ParseUser.getCurrentUser());
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        for(ParseObject object: objects){
//                            String username = object.get("First_name".toString());
//                            Log.i("fcatory", "username forlopo" + username);
//                        }
//                    }
//                });
                Log.i("ReqAdapt", "inside query");

                try {
                    query.find();
                    Log.i("searchReq", "found something ");
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("searchReq", "error find " + e );
                }
//                try {
//                    ParseObject user_request = query.get("Userrequest");
//                    String Username = user_request.getString("First_name");
//                    Log.i("searchRequest", "name of first user found " + Username);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    Log.e("searchreq","error " +  e);
                return query;
            }
        };

        return queryFactory;

    }

}
