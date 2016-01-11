package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    EditText mUsernameField;
    EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = (EditText)findViewById(R.id.register_username);
        mPasswordField = (EditText)findViewById(R.id.register_password);

    }


    public void logIn(final View v){
        v.setEnabled(false);
        ParseUser.logInInBackground(mUsernameField.getText().toString(), mPasswordField.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    switch (e.getCode()) {
                        case ParseException.USERNAME_MISSING:
                            Toast.makeText(Login.this, "Sorry, you must supply a username", Toast.LENGTH_SHORT).show();
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
