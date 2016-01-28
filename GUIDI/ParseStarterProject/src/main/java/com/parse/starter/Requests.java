package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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


    public void returnToMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }




    @Override
    public void DataChanged() {
        requestAdapter.notifyDataSetChanged();
        requestAdapter = new RequestAdapter(this, search.searchMatch(), this);
        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();
    }
}
