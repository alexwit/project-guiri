package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseObject;

public class Requests extends ListActivity implements OnDataChanged {

    RequestAdapter requestAdapter;
    Search search;


    // Todo can be put directly into main activity ?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);


//        queryList = new ArrayList<ParseQuery<ParseUser>>();
//        superQuery = ParseQuery.getQuery("_User");

        ParseObject.registerSubclass(DataBase.class);

        search = new Search();


        requestAdapter = new RequestAdapter(this, search.searchMatch(), this);


        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();

    }


    public void returnToMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }


// todo kan er uit?
//    public ParseQueryAdapter.QueryFactory queryFactory(){
//        final ParseQueryAdapter.QueryFactory queryFactory = new ParseQueryAdapter.QueryFactory() {
//            @Override
//            public ParseQuery create() {
//                ParseQuery query = ParseQuery.getQuery("DataBase");
//                Log.i("Seearch adapt", " current user" + ParseUser.getCurrentUser().toString());
//                query.whereEqualTo("GuideMatchId", ParseUser.getCurrentUser().toString());
//                query.whereNotEqualTo("Declineduser", true);
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if (e == null) {
//                            for (ParseObject object : objects) {
//                                String objectId = object.getObjectId();
//                                ParseQuery nextQuery = ParseQuery.getQuery("_User");
//                                nextQuery.whereEqualTo("objectId", objectId);
//                                queryList.add(nextQuery);
//                            }
//                            superQuery.or(queryList);
//                            requestAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//
//                });
//
//                return query;
//            }
//        };
//        return queryFactory;
//    }


//
//    public ArrayList<ParseQuery> searchRequests() {
//
////        List<ParseObject> AL = new List<ParseObject>() {
//        final ArrayList<ParseQuery> queryList = new ArrayList<ParseQuery>();
////            public ParseQuery create() {
//        ParseQuery query = ParseQuery.getQuery("DataBase");
//        Log.i("Seearch adapt", " current user" + ParseUser.getCurrentUser().toString());
//        query.whereEqualTo("GuideMatchId", ParseUser.getCurrentUser().toString());
//        query.whereNotEqualTo("Declineduser", true);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    for (ParseObject object : objects) {
//                        String objectId = object.getObjectId();
//                        ParseQuery nextQuery = ParseQuery.getQuery("_User");
//                        nextQuery.whereEqualTo("objectId", objectId);
//                        queryList.add(nextQuery);
//                    }
//                    requestAdapter.notifyDataSetChanged();
//                }
//
//            }
//
//        });
//
//        return queryList;
//    }


    @Override
    public void DataChanged() {
        requestAdapter.notifyDataSetChanged();
        requestAdapter = new RequestAdapter(this, search.searchMatch(), this);
        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();
    }
}
