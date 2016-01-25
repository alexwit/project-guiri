package com.parse.starter;

import android.app.ListActivity;
import android.os.Bundle;

import com.parse.ParseQueryAdapter;

public class Requests extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        Search search = new Search();

        ParseQueryAdapter.QueryFactory requestFactory = search.searchRequests();

        RequestAdapter requestAdapter = new RequestAdapter(this, requestFactory);

        setListAdapter(requestAdapter);
        requestAdapter.loadObjects();

    }
}
