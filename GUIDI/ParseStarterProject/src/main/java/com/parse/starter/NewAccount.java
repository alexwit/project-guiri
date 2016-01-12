package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

public class NewAccount extends AppCompatActivity {

    EditText mNameUserField;
    EditText mAgeUserField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        ParseObject.registerSubclass(DataBase.class);

        mNameUserField = (EditText)findViewById(R.id.account_username);
        mAgeUserField = (EditText)findViewById(R.id.account_age);

    }

    public void createAccount(View v){
        // todo make check for letters in username and think of more checks
        if (mNameUserField.getText().length() == 0 || mAgeUserField.getText().length() == 0 || Integer.parseInt(mAgeUserField.getText().toString()) < 18){
            Toast.makeText(NewAccount.this, "Your input is incorrect", Toast.LENGTH_SHORT).show();
            return;}

        DataBase t = new DataBase();
        t.setName(mNameUserField.getText().toString());
        t.setAge(Integer.parseInt(mAgeUserField.getText().toString()));
        t.setCompleted(false);
        t.saveEventually();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
