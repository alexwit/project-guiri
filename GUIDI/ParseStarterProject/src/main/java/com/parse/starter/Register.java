package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {
    EditText mUsernameField;
    EditText mPasswordField;
    EditText mEmailField;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        if(ParseUser.getCurrentUser().getUsername() == null){
////            intent = new Intent(this, Login.class);
//        }else{
//            Log.i("REgister", "currentUser not null");
//            intent = new Intent(this, MainActivity.class);
//        }
//
//        startActivity(intent);

        mUsernameField = (EditText)findViewById(R.id.register_username);
        mPasswordField = (EditText)findViewById(R.id.register_password);
        mEmailField = (EditText)findViewById(R.id.register_email);


    }

    public void register(final View v) {
        if (mUsernameField.getText().length() == 0 || mPasswordField.getText().length() == 0 )
          //|| mEmailField.getText().length() == 0

            return;

        v.setEnabled(false);
        ParseUser user = new ParseUser();
        user.setUsername(mUsernameField.getText().toString());
        user.setPassword(mPasswordField.getText().toString());
        user.setEmail(mEmailField.getText().toString());


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(Register.this, NewAccount.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    switch (e.getCode()) {
                        case ParseException.USERNAME_TAKEN:
                            Toast.makeText(Register.this, "Sorry, this username has already been taken", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.USERNAME_MISSING:
                            Toast.makeText(Register.this, "Sorry, you must supply a username", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.PASSWORD_MISSING:
                            Toast.makeText(Register.this, "Sorry, you must supply a password", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.EMAIL_TAKEN:
                            Toast.makeText(Register.this, "Sorry, this email adress is allready taken", Toast.LENGTH_SHORT).show();
                            break;
                        case ParseException.OBJECT_NOT_FOUND:
                            Toast.makeText(Register.this, "Those credentials were invalid", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Register.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    v.setEnabled(true);
                }
            }
        });
    }

    public void showLogin(View v){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
