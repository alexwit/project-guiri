package com.parse.starter;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Requests extends ListActivity {

    RequestAdapter requestAdapter;
    ParseQuery superQuery;
    List<ParseQuery<ParseUser>> queryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);


        queryList = new ArrayList<ParseQuery<ParseUser>>();
        superQuery = ParseQuery.getQuery("_User");

        ParseObject.registerSubclass(DataBase.class);


        requestAdapter = new RequestAdapter(this, queryFactory());


        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();

    }




    public ParseQueryAdapter.QueryFactory queryFactory(){
        final ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
            @Override
            public ParseQuery create() {
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
                            superQuery.or(queryList);
                            requestAdapter.notifyDataSetChanged();
                        }

                    }

                });

                return query;
            }
        };
        return queryFactory;
    }



    public ArrayList<ParseQuery> searchRequests() {

//        List<ParseObject> AL = new List<ParseObject>() {
        final ArrayList<ParseQuery> queryList = new ArrayList<ParseQuery>();
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
                    requestAdapter.notifyDataSetChanged();
                }

            }

        });

        return queryList;
    }


}
