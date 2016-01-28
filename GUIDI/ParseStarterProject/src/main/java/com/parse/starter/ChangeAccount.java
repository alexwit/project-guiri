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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


// Alex Wittebrood #10288880

public class ChangeAccount extends ActionBarActivity {

    EditText mNameUser;
    EditText mSurnameUser;
    EditText mAgeUser;
    EditText mCityUser;
    EditText mCountryUser;
    EditText mPerInfo;

    String name;
    String surname;
    String age;
    String city;
    String country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);

        // gets al account information of previous activity
        name = getIntent().getExtras().getString("name");
        surname = getIntent().getExtras().getString("surname");
        age = getIntent().getExtras().getString("age");
        city = getIntent().getExtras().getString("city");
        country = getIntent().getExtras().getString("country");


        mNameUser = (EditText) findViewById(R.id.account_username);
        mSurnameUser = (EditText) findViewById(R.id.account_surname);
        mAgeUser = (EditText) findViewById(R.id.account_age);
        mCityUser = (EditText) findViewById(R.id.account_city);
        mCountryUser = (EditText) findViewById(R.id.account_country);
        mPerInfo = (EditText) findViewById(R.id.account_persinfo);


        // sets al account information
        mAgeUser.setText(String.valueOf(age));
        mNameUser.setText(name);
        mSurnameUser.setText(surname);
        mCityUser.setText(city);
        mCountryUser.setText(country);


    }

    // Changes the information that the user changed and saves it on Parse.com
    public void changeAccount(View v) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (mNameUser.getText().length() > 0 ) {
            currentUser.put("First_name", mNameUser.getText().toString());
        }else if(mSurnameUser.getText().length() > 0){
            currentUser.put("Surname", mSurnameUser.getText().toString());
        } else if (mAgeUser.getText().length() > 0) {
            currentUser.put("Age", Integer.parseInt(mAgeUser.getText().toString()));
        } else if (mCityUser.getText().length() > 0 ) {
            currentUser.put("City", mCityUser.getText().toString());
        } else if (mCountryUser.getText().length() > 0 ) {
            currentUser.put("Country", mCountryUser.getText().toString());
        } else if (mPerInfo.getText().length() > 0 || mPerInfo.getText().toString().equals("Write something about yourself..")) {
            currentUser.put("PersInfo", mPerInfo.getText().toString());
        }


        currentUser.saveInBackground(new SaveCallback() {
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
        if (id == R.id.action_Main) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        if (id == R.id.action_matches){
            Intent i = new Intent(this, MatchList.class);
            startActivity(i);
            finish();
        }

        if (id == R.id.action_account) {
            Intent i = new Intent(this, Account.class);
            startActivity(i);
            finish();
        }
        if(id == R.id.action_logout){
            ParseUser.logOut();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        if(id== R.id.action_request){
            Intent i = new Intent(this, Requests.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}


