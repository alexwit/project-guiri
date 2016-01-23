package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ChangeAccount extends AppCompatActivity {

    EditText mNameUser;
    EditText mAgeUser;

    String name;
    Integer age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);

        name = getIntent().getExtras().getString("name").toString();
        age = getIntent().getExtras().getInt("age");


        mNameUser = (EditText)findViewById(R.id.account_username);
        mAgeUser = (EditText)findViewById(R.id.account_age);
        // todo age is not right
        mAgeUser.setText(String.valueOf(age));
        mNameUser.setText(name);

    }

    public void changeAccount(View v) {

        final DataBase account = new DataBase();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DataBase");

        // Retrieve the object by id
//        Log.i("changeaccount ", "id is " + account.getId().toString());
        query.getInBackground(ParseUser.getCurrentUser().getObjectId(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    if (mNameUser.getText().length() <= 0 || !(mNameUser.getText().equals(name))) {
                        account.put("name", mNameUser.getText().toString());

                    } else if (mAgeUser.getText().length() > 0 || !(mAgeUser.getText().equals(age))) {
                        account.put("Age", Integer.parseInt(mAgeUser.getText().toString()));
                    }
                    account.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Saved successfully.
                                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                            } else {
                                // The save failed.
                                Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                                Log.d(getClass().getSimpleName(), "User update error: " + e);
                            }
                        }
                    });
                } else {
                    Toast.makeText(ChangeAccount.this, "something wet wrong", Toast.LENGTH_SHORT).show();
                    Log.e("changeAccount", "error " + e);
                }
            }


        });
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


