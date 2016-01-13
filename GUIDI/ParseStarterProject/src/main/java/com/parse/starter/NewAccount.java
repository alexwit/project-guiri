package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
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
        ParseObject.registerSubclass(DataBase.class);

        mFirstNameField = (EditText)findViewById(R.id.newaccount_firstname);
        mSurnameField = (EditText)findViewById(R.id.newaccount_surname);
        mAgeUserField = (EditText)findViewById(R.id.newaccount_age);
        mCountryUserField = (EditText)findViewById(R.id.newaccount_country);
        mCityUserField = (EditText)findViewById(R.id.newaccount_city);
    }

    public void createAccount(View v){
        // todo make check for letters in username and think of more checks

        if (mFirstNameField.getText().length() == 0 || mAgeUserField.getText().length() == 0
                || Integer.parseInt(mAgeUserField.getText().toString()) < 18
                || mSurnameField.getText().length() == 0 ||
                mCountryUserField.getText().length() == 0 || mCityUserField.getText().length() == 0){
            Toast.makeText(NewAccount.this, "Your input is incorrect", Toast.LENGTH_SHORT).show();
            return;}

        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("New Account", currentUser.toString());
        if(currentUser != null) {
            currentUser.put("First_name", "hoi");
            currentUser.put("Surname", (mSurnameField.getText().toString()));
            currentUser.put("Age", mAgeUserField.getInputType());
            currentUser.put("Country", mCountryUserField.getText().toString());
            currentUser.put("City", mCityUserField.getText().toString());
            currentUser.saveInBackground();
        }
        else{
            Toast.makeText(NewAccount.this, "There is something wrong!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
