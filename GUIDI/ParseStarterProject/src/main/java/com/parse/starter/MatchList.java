package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseQueryAdapter;

// Alex Wittebrood #10288880
// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class MatchList extends ListActivity {

    AcceptAdapter acceptAdapter;
    ParseQueryAdapter.QueryFactory queryFactory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        Search search = new Search();
        // creates adapter for list view with
        queryFactory = search.searchAccepted();
        acceptAdapter = new AcceptAdapter(this, queryFactory);

        // Creates list with correct data
        setListAdapter(acceptAdapter);
        acceptAdapter.loadObjects();

    }


    public void returnToTheMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
