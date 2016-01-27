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
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    Search search;
    EditText mCityField;
    List<ParseUser> mAccountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        mCityField = (EditText)findViewById(R.id.mCityField);


    }

    public void searchCity(View v){

        if(mCityField.getText().toString().length()!=0) {

            Intent intent = new Intent(this, SearchList.class);
            intent.putExtra("City", mCityField.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "You did not enter a city!", Toast.LENGTH_SHORT).show();
        }
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
        if (id == R.id.action_matches){
            Intent i = new Intent(MainActivity.this, AcceptedGuide.class);
            startActivity(i);
        }

        if (id == R.id.action_account) {

        Intent i = new Intent(MainActivity.this, Account.class);
        startActivity(i);
        }
        if(id == R.id.action_logout){

            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {

                    Log.e("main", "error " + e);
                    Toast.makeText(MainActivity.this, "Log out malfunction", Toast.LENGTH_SHORT).show();
                }
            });
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
            finish();
            return true;
        }
        if(id== R.id.action_request){
            Intent i = new Intent(MainActivity.this, Requests.class);
            startActivity(i);
        }

      return super.onOptionsItemSelected(item);
    }
}
