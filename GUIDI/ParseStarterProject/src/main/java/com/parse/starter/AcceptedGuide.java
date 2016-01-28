package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class AcceptedGuide extends ListActivity {

    AcceptAdapter acceptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_guide);

        Search search = new Search();

        acceptAdapter = new AcceptAdapter(this, search.searchAcceptance());

        setListAdapter(acceptAdapter);
        acceptAdapter.loadObjects();

    }


    public void returnToTheMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
