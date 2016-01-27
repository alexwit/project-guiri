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

public class GuideAccount extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;
    TextView mCityUser;
    TextView mCountryUser;

    String nameGuideUser;
    Integer age;
    String objectId;
    String emailCurrUser;
    String eMailGuideUser;
    String surnameGuideUser;
    String cityGuideUser;
    String countryGuideUser;
    Integer ageGuideUser;

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
        emailCurrUser = ParseUser.getCurrentUser().getEmail();
        Log.i("guide ", "im here ");
        // Gets the account information of the inspected user
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                // todo make sure that no input needed to be checked
                if (e == null) {
                    nameGuideUser = object.getString("First_name");
                    surnameGuideUser = object.getString("Surname");
                    cityGuideUser = object.getString("City");
                    Log.i("guide", "city " + cityGuideUser);
                    countryGuideUser = object.getString("Country");
                    eMailGuideUser = object.getString("email");
                    ageGuideUser = object.getInt("Age");

                    // todo Checks kunnen eruit
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
            DataBase profile = new DataBase();
            profile.put("UserReqId", ParseUser.getCurrentUser().getObjectId());
            profile.put("GuideMatchId", objectId);
            profile.put("Email", emailCurrUser);
            profile.put("TouristName", ParseUser.getCurrentUser().get("First_name"));
            profile.put("TouristSurname", ParseUser.getCurrentUser().get("Surname"));
            profile.put("TouristCity", ParseUser.getCurrentUser().get("City"));
            profile.put("TouristCountry", ParseUser.getCurrentUser().get("Country"));
            profile.put("GuideName", nameGuideUser);
            profile.put("GuideSurname", surnameGuideUser);
            profile.put("GuideCity", cityGuideUser);
            profile.put("GuideCountry", countryGuideUser);
            profile.put("EmailGuide", eMailGuideUser);

            // sets the information to be writeable and readable by different users
            ParseACL acl = new ParseACL();
            acl.setPublicReadAccess(true);
            acl.setPublicWriteAccess(true);
            profile.setACL(acl);
            profile.setAcceptUserFalse();
            profile.setDecilinedUserFalse();
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





    public void sendEmail(View view) {


                // todo  Haal er misschien maar uit
//        JSONObject data = new JSONObject();
//        try {
//            data.put("action", "com.bt.yana.GOT_MESSAGE");
//            data.put("from", ParseUser.getCurrentUser().getUsername());
//        }catch (Exception e){
//            e.printStackTrace();
//            return;
//        }
//
//
//        ParsePush parsePush = new ParsePush();
//        parsePush.setData(data);
//
//        ParseQuery<ParseInstallation> parseQuery = ParseQuery.getQuery(ParseInstallation.class);
//        if(toUser!=null) {
//            parseQuery.whereEqualTo("username", toUser);
//            parsePush.setQuery(parseQuery);
//            parsePush.sendInBackground();
//        }


            }

            public void getReviews(View view) {
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
        if (id == R.id.action_Main){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_account) {

            Intent i = new Intent(this, Account.class);
            startActivity(i);
        }
        if(id == R.id.action_logout){
            ParseUser.logOut();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
