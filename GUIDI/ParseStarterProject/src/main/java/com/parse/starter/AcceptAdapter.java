package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by Papi lexus on 27-1-2016.
 * Alex Wittebrood student# 10288880
 * // http://michaelevans.org/blog/2013/08/14/tutorial-building-an-android-to-do-list-app-using-parse/
 *
 */
public class AcceptAdapter extends ParseQueryAdapter<ParseObject> {

    // Custom adapter
    public AcceptAdapter(Context context, ParseQueryAdapter.QueryFactory queryFactory) {
        // Adapter gets all account information from the QueryFactory
        super(context, queryFactory);

    }

    // Customizes the layout and functionallity of the specific items
    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.match_list_item, null);
        }

        super.getItemView(object, v, parent);

        // Add the the name  of the view
        TextView nameTextView = (TextView) v.findViewById(R.id.account_name_description);
        TextView ageTextView = (TextView) v.findViewById(R.id.account_age_description);
        TextView cityTextView = (TextView) v.findViewById(R.id.account_city_description);
        TextView countryTextView = (TextView) v.findViewById(R.id.account_country_description);
        TextView emailTextView = (TextView) v.findViewById(R.id.account_email_description);
        Button emailSendBtn = (Button) v.findViewById(R.id.btn_sendemail);

        // sets the guide information
        nameTextView.setText(object.getString("GuideName") + " " + object.get("GuideSurname"));
        ageTextView.setText(String.valueOf(object.getInt("GuideAge")));
        cityTextView.setText(String.valueOf(object.getString("GuideCity")));
        countryTextView.setText(object.getString("GuideCountry"));
        emailTextView.setText(object.getString("EmailGuide"));

        emailSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http:stackoverflow.com/questions/4440173/mfmailcomposeviewcontroller-in-android
                // Send an email with your gmail or other mailing app on your phone.
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{object.getString("EmailGuide")});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I want to get in touch!");
                    emailIntent.putExtra(Intent.EXTRA_TEXT,
                            "I want to get in contact with you, my email is: " +
                                    ParseUser.getCurrentUser().getEmail());

                    emailIntent.setType("text/plain");
                    getContext().startActivity(emailIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    //Theres no email client installed on users device.
                    Log.e("acceptadapter", "error " + ex);
                    Toast.makeText(getContext(), "Mail wasn't send", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}