package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Account extends AppCompatActivity {

    EditText mNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ParseObject.registerSubclass(DataBase.class);

        mNameInput = (EditText)findViewById(R.id.account_username);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("DataBase");
        query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
//                    // object will be your game score
//                    object.get()

                } else {
                    // something went wrong
                }
            }
        });
    }






    public void changeAccount(View v) {
        if (mNameInput.getText().length() > 0){
            DataBase t = new DataBase();
            t.setName(mNameInput.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
            mNameInput.setText("");
        }
    }



}
