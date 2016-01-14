package com.parse.starter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Papi lexus on 14-1-2016.
 */

// http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/

public class ProfileAdapter extends ArrayAdapter<Search> {

    private Context mContext;
    private List<ParseUser> mAccounts;

    public ProfileAdapter(Context context, List<ParseObject> objects) {
        super(context, R.layout.account_rows, objects);
        this.mContext = context;
        this.mAccounts = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.account_rows, null);
        }


        Search profile = mAccounts.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.account_description);

        descriptionView.setText(profile.getString());

        if(task.isCompleted()){
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }

}
}
