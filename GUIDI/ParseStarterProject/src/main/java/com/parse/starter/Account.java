package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Account extends AppCompatActivity {

    TextView mNameUser;
    TextView mAgeUser;

    String name;
    Integer age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ParseObject.registerSubclass(DataBase.class);

        mNameUser = (TextView) findViewById(R.id.account_username);
        mAgeUser = (TextView) findViewById(R.id.account_age);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//        query.whereEqualTo("ACL", ParseUser.getCurrentUser().getObjectId().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {

                Log.i("account", "objectid of user, check if  it is the same " + ParseUser.getCurrentUser().getObjectId().toString());

                if (e == null) {
                    int henk = objects.size();
                    Log.i("account", "grote van lijst " + henk) ;
                    for (ParseObject object : objects) {
                        name = object.getString("name");
                        Log.i("account", "account name " + name);
                        if (name != null) {
                            mNameUser.setText(name);
                        }
                        age = object.getInt("age");
                        if (age != null) {
                          mAgeUser.setText(age.toString());
                        }
                    }
                } else {
                    // something went wrong)
                    Log.e("Account", "did not get new name, error: " + e);
                }
            }
        });

    }
//        ParseQuery<ParseUser> query = ParseUser.getQuery();
//        query.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
////        Log.i("account", "objectACL of user " + ParseUser.getCurrentUser().getACL().toString());
//        Log.i("account", "objectid of user " + ParseUser.getCurrentUser().getObjectId().toString());
//        query.getInBackground(ParseUser.getCurrentUser().getObjectId().toString(),new GetCallback<ParseUser>() {
//            public void done(ParseUser object, ParseException e) {
//                Log.i("account", "objectid of user, check if  it is the same " + ParseUser.getCurrentUser().getObjectId().toString());
//                if (e == null) {
//                    // Gets Account specifications from Parse
//                    DataBase profile = new DataBase();
//                    String acl = profile.getObjectId().toString();
//                    Log.i("account", "OBject id account  " + acl);
//                    name = profile.getString("name");
//                    Log.i("account", "account name " + name);
//                    if (name != null) {
//                        mNameUser.setText(name);
//                    }
//                    //age = object.getInt("age");
////                    if (age != null) {
////                        mAgeUser.setText(age);
////                    }
//                } else {
//                    // something went wrong)
//                    Log.e("Account", "did not get new name, error: " + e);
//                }
//            }
//        });
//    }



    public void toChangeAccount(View v) {

        Intent intent = new Intent(this, ChangeAccount.class);
        intent.putExtra("name",name);
//        intent.getStringExtra("name", name);
        intent.getIntExtra("age", age);
        startActivity(intent);

//        TODO: THIS IS TO ADD INFO TO DATABASE
//        if (mNameInput.getText().length() > 0){
//            DataBase t = new DataBase();
//            t.setName(mNameInput.getText().toString());
//            t.setCompleted(false);
//            t.saveEventually();
//            mNameInput.setText("");
//        }
    }



}
