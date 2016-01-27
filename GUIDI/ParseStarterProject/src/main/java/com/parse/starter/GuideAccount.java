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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class GuideAccount extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;

    String name;
    Integer age;
    String objectId;
    String emailCurrUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_account);
        ParseObject.registerSubclass(DataBase.class);

        objectId = getIntent().getExtras().getString("ID").toString();

        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);
        emailCurrUser = ParseUser.getCurrentUser().getEmail();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
               Log.i("guide", "objectid of user, check if  it is the same " + objectId);

                if (e == null) {
                    name = object.getString("First_name");
                    Log.i("guide", "account name " + name);

                    if (name != null) {
                        mNameUser.setText(name);
                    }
                    age = object.getInt("Age");
                    if (age != null) {
                        mAgeUser.setText(age.toString());
                    }
                }
                else {
                    // something went wrong)
                    Log.e("Account", "did not get new name, error: " + e);
                }
            }
        });

    }

    public void sendRequest(View view) {


        DataBase profile = new DataBase();
        profile.put("Userrequest", ParseUser.getCurrentUser());
        profile.put("Guidematch", objectId);
        profile.put("Email", emailCurrUser);
        profile.put("TouristName", ParseUser.getCurrentUser().get("First_name"));
        profile.put("TouristSurname", ParseUser.getCurrentUser().get("Surname"));
        profile.put("TouristCity", ParseUser.getCurrentUser().get("City"));
        profile.put("TouristCountry", ParseUser.getCurrentUser().get("Country"));
        profile.setAcceptUserFalse();
        profile.setDecilinedUserFalse();
        profile.saveInBackground();
        Toast.makeText(this, "Send Request", Toast.LENGTH_SHORT);

    }





    public void sendEmail(View view) {


                // todo IK SNAP HIER GEEN Coño van
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
