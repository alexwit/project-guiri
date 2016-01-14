package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Papi lexus on 13-1-2016.
 */
public class Search {


    List<ParseUser> locals = null;


    public List<ParseUser> searchUsers(String city){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("City", city);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> userList, ParseException e) {
                if (e == null) {
                    Log.i("main", "inside if loop" + userList);

                    for (ParseUser user : userList) {
                        Log.i("main", "inside for loop");
                        String username = user.getString("username");
                        Log.i("main", "username " + username);

                    }
                    locals = userList;
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        return locals;
    }


}
