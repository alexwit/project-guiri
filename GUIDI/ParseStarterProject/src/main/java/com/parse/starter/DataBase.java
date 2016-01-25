package com.parse.starter;

/**
 * Created by Papi lexus on 11-1-2016.
 */
//http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;


//http://blog.parse.com/announcements/parse-on-android-just-got-classier/

@ParseClassName("DataBase")
public class DataBase extends ParseObject{
    public DataBase(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public String getId() {
        return getObjectId();
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

    public void setFalse() {put("Acceptuser", false);}

    public void setName(String name){
        put("name", name);
    }

    public  void setCountry(String country){put("country", country);}

    public String getCountry(){return getString("country");}

    public void setCity(String city){put("city", city);}

    public String getCity(){return getString("city");}

    public ParseUser getUser() {
        return getParseUser("User");
    }
    public void setUser(ParseUser user) {
        put("user", user);
    }
}

