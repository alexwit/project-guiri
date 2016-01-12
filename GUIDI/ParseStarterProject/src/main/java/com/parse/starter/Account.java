package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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

        mNameUser = (TextView)findViewById(R.id.account_username);
        mAgeUser = (TextView)findViewById(R.id.account_age);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("DataBase");
        query.getInBackground("N1rmSxS7ps", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // Gets Account specifications from Parse
                    name = object.getString("name");
                    if(name != null) {
                        mNameUser.setText(name);
                    }
                    //age = object.getInt("age");
//                    if (age != null) {
//                        mAgeUser.setText(age);
//                    }
                } else {
                    // something went wrong
                    Log.e("Account","did not get new name " + object.getString("name"));
                }
            }
        });
    }



    public void toChangeAccount(View v) {

        Intent intent = new Intent(this, ChangeAccount.class);
        intent.getStringExtra(name);
        intent.getIntExtra("HUH?!", age);
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
