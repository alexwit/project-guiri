package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Account extends ActionBarActivity {

    TextView mNameUser;
    TextView mAgeUser;
    TextView mCityUser;
    TextView mCountryUser;

    String name;
    Integer age;
    String city;
    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ParseObject.registerSubclass(DataBase.class);

        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);
        mCityUser = (TextView) findViewById(R.id.account_city);
        mCountryUser = (TextView)findViewById(R.id.account_country);

        // gets account information of the current user
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null){
                    // checks if information is not null
                    name = object.getString("First_name");
                    Log.i("account", "account name " + name);
                    if (name != null) {
                        mNameUser.setText(name);
                    }
                    age = object.getInt("Age");
                    if (age != null) {
                        mAgeUser.setText(age.toString());
                    }
                    city = object.getString("City");
                    if(city != null){
                        mCityUser.setText(city);
                    }
                    country = object.getString("Country");
                    if(country !=null){
                        mCountryUser.setText(country);
                    }
                }
            }
        });

    }


    public void returnMain(View v){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


    public void toChangeAccount(View v) {

        Intent intent = new Intent(this, ChangeAccount.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age.toString());
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        startActivity(intent);

    }



}
