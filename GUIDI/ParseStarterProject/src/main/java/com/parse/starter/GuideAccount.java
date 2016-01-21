package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class GuideAccount extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;

    String name;
    Integer age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ParseObject.registerSubclass(DataBase.class);

        final String objectId = getIntent().getExtras().getString("ID").toString();

        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);


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
}
