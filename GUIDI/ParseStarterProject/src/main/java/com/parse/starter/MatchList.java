package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// Alex Wittebrood #10288880

public class MatchList extends ListActivity {

    AcceptAdapter acceptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        Search search = new Search();

        acceptAdapter = new AcceptAdapter(this, search.searchAcceptance());

        setListAdapter(acceptAdapter);
        acceptAdapter.loadObjects();

    }


    public void returnToTheMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
