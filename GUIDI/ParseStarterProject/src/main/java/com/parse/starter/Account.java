package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class Account extends ActionBarActivity {

    TextView mNameUser;
    TextView mAgeUser;
    TextView mCityUser;
    TextView mCountryUser;
    TextView mPersInfo;

    String name;
    String surname;
    Integer age;
    String city;
    String country;
    String persInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ParseObject.registerSubclass(DataBase.class);

        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);
        mCityUser = (TextView) findViewById(R.id.account_city);
        mCountryUser = (TextView)findViewById(R.id.account_country);
        mPersInfo = (TextView)findViewById(R.id.account_persinfo);

        // gets account information of the current user
        final ParseUser currentUser = ParseUser.getCurrentUser();

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//        query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseObject>() {
//            @Override
//            public void done(ParseObject object, ParseException e) {
//                if(e==null){
                if(currentUser!=null){
                    // checks if information is not null
                    name = currentUser.getString("First_name");
                    surname = currentUser.getString("Surname");
                    if (name != null) {
                        mNameUser.setText(name + " " + surname);
                    }

                    age = currentUser.getInt("Age");
                    if (age != null) {
                        mAgeUser.setText(age.toString());
                    }
                    city = currentUser.getString("City");
                    if(city != null){
                        mCityUser.setText(city);
                    }
                    country = currentUser.getString("Country");
                    if(country !=null){
                        mCountryUser.setText(country);
                    }
                    persInfo = currentUser.getString("PersInfo");
                    if(persInfo != null){
                        mPersInfo.setText(persInfo);
                    }
                    else{
                        mPersInfo.setText("No personal info given yet.");
                    }
                }
                else{
                    Intent i = new Intent(this, Login.class);}
            }

    public void returnMain(View v){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }


    // Sends Original input of user to ChangeAccount class
    public void toChangeAccount(View v) {

        Intent intent = new Intent(this, ChangeAccount.class);
        intent.putExtra("name", name);
        intent.putExtra("age", age.toString());
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("surname", surname);
        startActivity(intent);
        finish();

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
