//package com.parse.starter;
//
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SearchList extends ListActivity {
//
//    List users = new ArrayList<DataBase>();
//    private String city;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_list);
//
//        city = getIntent().getStringExtra("City");
//
//        ParseObject.registerSubclass(DataBase.class);
//
//        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("DataBase");
//        query.whereEqualTo("City", city);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e != null){
//                    Toast.makeText(SearchList.this, "error " + e, Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    for (ParseObject object : objects) {
//                        object.getClass();
//                        user.setName(object.getName());
//                        users.add(user);
//                        ParseObject.
//                    }
//
//                    ArrayAdapter<ParseObject> adapter = new ArrayAdapter<>(SearchList.this, android.R.layout.simple_list_item_1, users);
//                    setListAdapter(adapter);
//                }
//            }
//
//        });
//
//    }
//
//}