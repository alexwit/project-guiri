package com.parse.starter;

/**
 * Created by Papi lexus on 11-1-2016.
 * Alex Wittebrood #10288880
 */
//http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

import com.parse.ParseClassName;
import com.parse.ParseObject;

// Alex Wittebrood #10288880
//http://blog.parse.com/announcements/parse-on-android-just-got-classier/
// Sets
@ParseClassName("DataBase")
public class DataBase extends ParseObject{
    public DataBase(){

    }

    public void setAcceptUserFalse() {put("Acceptuser", false);}

    public void setDeclinedUserFalse() {put("Declineduser", false);}

}

