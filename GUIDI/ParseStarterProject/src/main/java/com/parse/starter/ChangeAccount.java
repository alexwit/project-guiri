package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ChangeAccount extends AppCompatActivity {

    EditText mNameUser;
    EditText mAgeUser;

    String name;
    Integer age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);

        name = getIntent().getExtras().getString("name");
        age = getIntent().getExtras().getInt("age");

        mNameUser = (EditText)findViewById(R.id.account_username);
        mAgeUser = (EditText)findViewById(R.id.account_age);

        mAgeUser.setText(name);
        mNameUser.setText(age);

    }

    public void changeAccount(View v){
        DataBase t = new DataBase();
        if ( mNameUser.getText().length() <= 0 || !(mNameUser.getText().equals(name))){
            t.setName(mNameUser.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
        }
        else if(mAgeUser.getText().length() > 0 || !(mAgeUser.getText().equals(age))) {
            t.setAge(Integer.parseInt(mAgeUser.getText().toString()));
            t.setCompleted(false);
            t.saveEventually();
        }
    }



//    TODO: THIS IS TO ADD INFO TO DATABASE
//        if (mNameInput.getText().length() > 0){
//            DataBase t = new DataBase();
//            t.setName(mNameInput.getText().toString());
//            t.setCompleted(false);
//            t.saveEventually();
//            mNameInput.setText("");
//        }
}


