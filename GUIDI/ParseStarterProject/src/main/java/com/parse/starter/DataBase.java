package com.parse.starter;

/**
 * Created by Papi lexus on 11-1-2016.
 */
//http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("DataBase")
public class DataBase extends ParseObject{
    public DataBase(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete){
        put("completed", complete);
    }

    public Integer getAge() {
        return getInt("age");
    }

    public void setAge(Integer age) {put("age", age); }

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name", name);
    }

    public ParseUser getUser() {
        return getParseUser("User");
    }
    public void setUser(ParseUser user) {
        put("user", user);
    }
}

