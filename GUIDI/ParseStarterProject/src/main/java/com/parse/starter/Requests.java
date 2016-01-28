package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseObject;

// Alex Wittebrood # 10288880

public class Requests extends ListActivity implements OnDataChanged {

    RequestAdapter requestAdapter;
    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        ParseObject.registerSubclass(DataBase.class);

        search = new Search();

        requestAdapter = new RequestAdapter(this, search.searchRequests(), this);
        
        // Creates the list with the correct input
        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();

    }


    public void returnToMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


    // Checks if data is changed for the query, if so it refreshes the list view

    @Override
    public void DataChanged() {
        requestAdapter.notifyDataSetChanged();
        requestAdapter = new RequestAdapter(this, search.searchRequests(), this);
        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();
    }
}
