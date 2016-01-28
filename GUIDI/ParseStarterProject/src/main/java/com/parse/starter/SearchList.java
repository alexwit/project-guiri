package com.parse.starter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;

public class SearchList extends ListActivity{


    private String city;

    ArrayList<String> objectID;
    TextView cityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        city = getIntent().getStringExtra("City");

        cityTitle = (TextView)findViewById(R.id.results_city);
        cityTitle.setText(city);

        ParseObject.registerSubclass(DataBase.class);



        Search search = new Search();

        ParseQueryAdapter.QueryFactory queryFactory = search.searchUsers(city);

        ProfileAdapter mainAdapter = new ProfileAdapter(this, queryFactory);
        setListAdapter(mainAdapter);
        mainAdapter.loadObjects();
        objectID = mainAdapter.objectID;
        Log.i("searchlist", "size List " + objectID.size());

    }

    public void returntheMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //todo make switch case

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_Main){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if (id == R.id.action_matches){
            Intent i = new Intent(this, AcceptedGuide.class);
            startActivity(i);
        }

        if (id == R.id.action_account) {

            Intent i = new Intent(this, Account.class);
            startActivity(i);
        }
        if(id == R.id.action_logout){

            ParseUser.logOut();
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        if(id== R.id.action_request){
            Intent i = new Intent(this, Requests.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
