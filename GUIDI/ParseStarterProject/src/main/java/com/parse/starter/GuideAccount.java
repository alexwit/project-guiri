package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class GuideAccount extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;

    String name;
    Integer age;
    String objectId;

    List requests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_account);
        ParseObject.registerSubclass(DataBase.class);

        objectId = getIntent().getExtras().getString("ID").toString();

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

    public void sendRequest(View view) {
        // suppose we have a user we want to follow
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {

                    for(ParseUser otherUser : users){
                        Log.i("gacocunt", "user id " + otherUser.getObjectId());
                        if(otherUser.getObjectId().equals(objectId)) {

                            requests = otherUser.getList("Requests");

                            requests.add(ParseUser.getCurrentUser().getObjectId());

                            Log.i("guideacc", "list " + requests + " list size " + requests.size());
                            otherUser.put("Test", "dit werkt");
                            otherUser.put("Requests", requests);

                            Log.i("guideacc", "request list " + otherUser.getList("Request"));

                            otherUser.saveInBackground(new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        // Saved successfully.
                                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // The save failed.
                                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                                        Log.d("no save ", "User update error: " + e);
                                    }
                                }
                            });
                        }
                    }
                }
                else {
                    Log.e("guideacc", "error " + e);
                }
            }
        });
//                if (e == null) {
//                    Log.i("guideacc", "naam " + object.get("First_name").toString());
//
//                    requests = object.getList("Requests");
//
//                    requests.add(ParseUser.getCurrentUser().getObjectId());
//
//                    Log.i("guideacc", "list " + requests + " list size " + requests.size());
//                    object.put("Test", "dit werkt");
//                    object.put("Requests", requests);
//
//
//                    Log.i("guideacc", "request list " + object.getList("Request"));
//
//
//                    object.saveInBackground();
//
//
//                } else {
//                    Log.e("guideacc", "error " + e);
//                }
//
//            }
//        });
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
