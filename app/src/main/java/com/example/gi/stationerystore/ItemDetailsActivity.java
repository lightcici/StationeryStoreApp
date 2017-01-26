package com.example.gi.stationerystore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class ItemDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String user = pref.getString("user","null");
        String itemID = getIntent().getExtras().getString("ItemID");
        String description=getIntent().getExtras().getString("Description");
        Date now = new Date(System.currentTimeMillis());
        String status="Pending Approval";

        Button submit=(Button) findViewById(R.id.submit);
        EditText id=(EditText) findViewById(R.id.editText1);
        EditText des=(EditText) findViewById(R.id.editText2);

        id.setText(itemID);
        des.setText(description);

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return Discrepancy.getMaxPrice(params[0]);
            }
            @Override
            protected void onPostExecute(String result) {
                EditText value=(EditText) findViewById(R.id.editText3);
                value.setText(result);
            }
        }.execute(itemID);

        submit.setOnClickListener(new View.OnClickListener() {
            String discrepancyId=Discrepancy.getMaxDiscrepancyId();
            @Override
            public void onClick(View v) {

//                new AsyncTask<String[], Void, List<Item>>() {
//                    @Override
//                    protected List<Item> doInBackground(String[]... params) {
//
//
//                        String[] saveDisParameter={discrepancyId,};
//                        return Item.searchItemList(params[0],params[1]);
//                    }
//                    @Override
//                    protected void onPostExecute(List<Item> result) {
//                        SimpleAdapter adapter = new SimpleAdapter(ItemDetailsActivity.this,result,R.layout.listing,
//                                new String[]{"ItemID", "Description"},
//                                new int[]{R.id.textViewTitle,R.id.textViewPrice});
//                        setListAdapter(adapter);
//                    }
//                }.execute(s);
            }
        });
    }
}
