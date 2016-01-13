/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    List locals = null;

    EditText mCityField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        mCityField = (EditText)findViewById(R.id.mCityField);





    }


    public void searchCity(View v){
        searchUsers(mCityField.getText().toString());
        Log.i("main", "succesfully searched yo mamma");
    }

    public void searchUsers(String city){
        List userList;
        ParseQuery<ParseUser> query = ParseQuery.getQuery("DataBase");
        query.whereEqualTo("city", city);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, ParseException e) {
                if (e == null) {
                    Log.d("Main", "Retrieved " + userList.size() + "users");
                    locals = userList;
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        Log.i("main", "list first " + locals.get(0).toString() );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        return true;
        }
        if (id == R.id.action_account) {

        Intent i = new Intent(MainActivity.this, Account.class);
        startActivity(i);
        }
        if(id == R.id.action_logout){
            ParseUser.logOut();
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
        }

      return super.onOptionsItemSelected(item);
    }
}
