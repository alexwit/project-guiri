package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseUser;

public class NewAccount extends AppCompatActivity {


    EditText mFirstNameField;
    EditText mSurnameField;
    EditText mAgeUserField;
    EditText mCountryUserField;
    EditText mCityUserField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);


        mFirstNameField = (EditText)findViewById(R.id.newaccount_firstname);
        mSurnameField = (EditText)findViewById(R.id.newaccount_surname);
        mAgeUserField = (EditText)findViewById(R.id.newaccount_age);
        mCountryUserField = (EditText)findViewById(R.id.newaccount_country);
        mCityUserField = (EditText)findViewById(R.id.newaccount_city);
    }

//  Creates user account with object info implemented in user info
    public void createAccount(View v){
        // todo make check for letters in username and think of more checks

        if (mFirstNameField.getText().length() == 0 || mAgeUserField.getText().length() == 0
                || Integer.parseInt(mAgeUserField.getText().toString()) < 18
                || mSurnameField.getText().length() == 0 ||
                mCountryUserField.getText().length() == 0 || mCityUserField.getText().length() == 0){
            Toast.makeText(NewAccount.this, "Input is correct", Toast.LENGTH_SHORT).show();
            return;}

        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("New Account", currentUser.toString());
        if(currentUser != null) {
            currentUser.put("First_name", mFirstNameField.getText().toString());
            currentUser.put("Surname", (mSurnameField.getText().toString()));
            currentUser.put("Age",Integer.parseInt(mAgeUserField.getText().toString()));
            currentUser.put("Country", mCountryUserField.getText().toString());
            currentUser.put("City", mCityUserField.getText().toString());
            currentUser.setACL(new ParseACL(ParseUser.getCurrentUser()));
            currentUser.saveInBackground();
        }
        else{
            Toast.makeText(NewAccount.this, "There is something wrong!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
