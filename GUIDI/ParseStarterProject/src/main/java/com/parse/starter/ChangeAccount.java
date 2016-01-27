package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ChangeAccount extends ActionBarActivity {

    EditText mNameUser;
    EditText mAgeUser;
    EditText mCityUser;
    EditText mCountryUser;

    String name;
    String age;
    String city;
    String country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);

        // gets al account information of previous activity
        name = getIntent().getExtras().getString("name");
        age = getIntent().getExtras().getString("age");
        city = getIntent().getExtras().getString("city");
        country = getIntent().getExtras().getString("country");

        mNameUser = (EditText)findViewById(R.id.account_username);
        mAgeUser = (EditText)findViewById(R.id.account_age);
        mCityUser = (EditText)findViewById(R.id.account_city);
        mCountryUser = (EditText)findViewById(R.id.account_country);

        // sets al account information
        mAgeUser.setText(String.valueOf(age));
        mNameUser.setText(name);
        mCityUser.setText(city);
        mCountryUser.setText(country);

    }

    // Changes the information that the user changed and saves it on Parse.com
    public void changeAccount(View v) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        // Makes sure the information of the current user is changed
        query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // checks if the input is different and correct
                    if (mNameUser.getText().length() <= 0 || !(mNameUser.getText().equals(name))) {
                        object.put("First_name", mNameUser.getText().toString());

                    } else if (mAgeUser.getText().length() > 0 || !(mAgeUser.getText().equals(age))) {
                        object.put("Age", Integer.parseInt(mAgeUser.getText().toString()));
                    } else if (mCityUser.getText().length() <= 0 || !(mCityUser.getText().equals(city))) {
                        object.put("City", mCityUser.getText().toString());
                    } else if (mCountryUser.getText().length() <= 0 || !(mCountryUser.getText().equals(country))) {
                        object.put("City", mCountryUser.getText().toString());
                    }
                    object.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Saved successfully.
                                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ChangeAccount.this, MainActivity.class);
                                startActivity(i);
                            } else {
                                // The save failed.
                                Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                                Log.e("ChangeAccount", "User update error: " + e);
                            }
                        }
                    });
                } else {
                    Toast.makeText(ChangeAccount.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    Log.e("changeAccount", "error " + e);
                }
            }


        });
    }
//  // TODO: 27-1-2016  check dit morgen
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_Main) {
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
//        }
//        if (id == R.id.action_matches){
//            Intent i = new Intent(this, AcceptedGuide.class);
//            startActivity(i);
//        }
//
//        if (id == R.id.action_account) {
//
//            Intent i = new Intent(this, Account.class);
//            startActivity(i);
//        }
//        if(id == R.id.action_logout){
//            ParseUser.logOut();
//            Intent i = new Intent(this, Login.class);
//            startActivity(i);
//        }
//        if(id== R.id.action_request){
//            Intent i = new Intent(this, Requests.class);
//            startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}


