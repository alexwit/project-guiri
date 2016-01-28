package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    EditText mEmailField;
    EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("login", "user " + currentUser);


        mEmailField = (EditText)findViewById(R.id.register_username);
        mPasswordField = (EditText)findViewById(R.id.register_password);


        //todo Check user loged in and add Username in stead of email
    }

    public void goToRegister(View v){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }


    public void logIn(final View v){
        v.setEnabled(false);
        ParseUser.logInInBackground(mEmailField.getText().toString(), mPasswordField.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    switch (e.getCode()) {
                        case ParseException.EMAIL_MISSING:
                            Toast.makeText(Login.this, "Sorry, you must supply an email account", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.PASSWORD_MISSING:
                            Toast.makeText(Login.this, "Sorry, you must supply a password", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.OBJECT_NOT_FOUND:
                            Toast.makeText(Login.this, "Those credentials were invalid", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Login.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    v.setEnabled(true);
                }
            }
        });
    }

}
