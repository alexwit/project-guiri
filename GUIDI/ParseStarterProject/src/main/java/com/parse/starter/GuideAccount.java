package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

// Alex Wittebrood # 10288880

public class GuideAccount extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;
    TextView mCityUser;
    TextView mCountryUser;
    TextView mInfoUser;

    String nameGuideUser;
    String objectId;
    String emailCurrUser;
    String eMailGuideUser;
    String surnameGuideUser;
    String cityGuideUser;
    String countryGuideUser;
    Integer ageGuideUser;
    String GuideInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_account);
        ParseObject.registerSubclass(DataBase.class);

        objectId = getIntent().getExtras().getString("ID").toString();
        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);
        mCityUser = (TextView) findViewById(R.id.account_city);
        mCountryUser = (TextView) findViewById(R.id.account_country);
        mInfoUser =(TextView) findViewById(R.id.account_persinfo);

        emailCurrUser = ParseUser.getCurrentUser().getEmail();

        // Gets the account information of the inspected user
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    nameGuideUser = object.getString("First_name");
                    surnameGuideUser = object.getString("Surname");
                    cityGuideUser = object.getString("City");
                    countryGuideUser = object.getString("Country");
                    eMailGuideUser = object.getString("email");
                    ageGuideUser = object.getInt("Age");
                    // makes sure the input is not null
                    if (nameGuideUser != null) {
                        mNameUser.setText(nameGuideUser + " " + surnameGuideUser);
                    }
                    if (ageGuideUser != null) {
                        mAgeUser.setText(ageGuideUser.toString());
                    }
                    if (cityGuideUser !=null){
                        mCityUser.setText(cityGuideUser);
                    }
                    if(countryGuideUser !=null){
                        mCountryUser.setText(countryGuideUser);
                    }
                    if(GuideInfo !=null){
                        mInfoUser.setText(GuideInfo);
                    }
                    else{
                        mInfoUser.setText("No personal info to show");
                    }

                }
                else {
                    // something went wrong)
                    Toast.makeText(GuideAccount.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Account", "error: " + e);
                }
            }
        });

    }

    // makes sure that the request can only be send once
    private boolean sendRequest = false;


    public void sendRequest(View view) {

        // Creates new connection on Parse so the relation request is set.
        if(!sendRequest) {
            // Puts the information of the two users in the set.
            ParseUser currentUser =ParseUser.getCurrentUser();
            DataBase profile = new DataBase();

            // sets users ID's
            profile.put("UserReqId", currentUser.getObjectId());
            profile.put("GuideMatchId", objectId);

            // sets info current user
            profile.put("Email", emailCurrUser);
            profile.put("TouristName", currentUser.get("First_name"));
            profile.put("TouristSurname", currentUser.get("Surname"));
            profile.put("TouristAge", currentUser.get("Age"));
            profile.put("TouristCity", currentUser.get("City"));
            profile.put("TouristCountry", currentUser.get("Country"));

            // sets info of the Guide
            profile.put("GuideName", nameGuideUser);
            profile.put("GuideSurname", surnameGuideUser);
            profile.put("GuideCity", cityGuideUser);
            profile.put("GuideCountry", countryGuideUser);
            profile.put("GuideAge", ageGuideUser);
            profile.put("EmailGuide", eMailGuideUser);

            // sets the information to be writeable and readable by different users
            ParseACL acl = new ParseACL();
            acl.setPublicReadAccess(true);
            acl.setPublicWriteAccess(true);
            profile.setACL(acl);
            profile.setAcceptUserFalse();
            profile.setDeclinedUserFalse();
            profile.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Saved successfully.
                        Toast.makeText(getApplicationContext(), "Request send", Toast.LENGTH_SHORT).show();
                    } else {
                        // The save failed.
                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                        Log.i("request save ", "error " + e);
                    }
                }
            });
            Toast.makeText(this, "Send Request", Toast.LENGTH_SHORT);
            sendRequest = true;
        }
        else{
            Toast.makeText(GuideAccount.this, "You've allready send an request", Toast.LENGTH_SHORT).show();
        }
    }

    public void returnToSearch(View view) {
        Intent i = new Intent(this, SearchList.class);
        startActivity(i);
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

        int id = item.getItemId();

        if (id == R.id.action_Main){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

        if (id == R.id.action_account) {

            Intent i = new Intent(this, Account.class);
            startActivity(i);
            finish();
        }
        if (id== R.id.action_request){
            Intent i = new Intent(this, Requests.class);
            startActivity(i);
            finish();
        }
        if (id== R.id.action_matches){
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

        return super.onOptionsItemSelected(item);
    }


}
