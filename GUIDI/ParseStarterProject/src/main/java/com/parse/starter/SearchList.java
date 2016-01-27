package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchList extends ListActivity {

    List users = new ArrayList<DataBase>();
    private String city;

    ArrayList<String> objectID;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        city = getIntent().getStringExtra("City");

        ParseObject.registerSubclass(DataBase.class);

        Search search = new Search();

        ParseQueryAdapter.QueryFactory queryFactory = search.searchUsers(city);

        lv = getListView();

        ProfileAdapter mainAdapter = new ProfileAdapter(this, queryFactory);
        setListAdapter(mainAdapter);
        mainAdapter.loadObjects();
        objectID = mainAdapter.objectID;
        Log.i("searchlist", "size List " + objectID.size());

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        Log.i("SearchList", "inside listitemclick");
        String itemId = objectID.get(position);
        Log.i("Searchlist", "itemID " + itemId);
        Intent intent = new Intent(this, GuideAccount.class);
        intent.putExtra("ID", itemId);
        startActivity(intent);
    }

}
