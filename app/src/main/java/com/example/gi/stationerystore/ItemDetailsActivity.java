package com.example.gi.stationerystore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class ItemDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        final String user = pref.getString("user","null");
        final String user = "00242";
        String discrepancyId;
        final String itemID = getIntent().getExtras().getString("ItemID");
        final String description=getIntent().getExtras().getString("Description");
        final EditText r=(EditText) findViewById(R.id.editText4);
        final EditText q=(EditText) findViewById(R.id.editText);
        final TextView quantity=(TextView) findViewById(R.id.quantity);
        final TextView reason=(TextView) findViewById(R.id.reason);


        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        final String now=sDateFormat.format(new java.util.Date());
        final String status="Pending Approval";


        Button submit=(Button) findViewById(R.id.submit);
        EditText id=(EditText) findViewById(R.id.editText1);
        EditText des=(EditText) findViewById(R.id.editText2);
        final TextView discId=(TextView) findViewById(R.id.discrepancyId);

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

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return Discrepancy.getMaxDiscrepancyId();
            }
            @Override
            protected void onPostExecute(String result) {

                discId.setText(result);
            }
        }.execute();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] a= {discId.getText().toString()};
                String s= a[0].substring(1,6);
                if(q.getText().toString().equals("")&&r.getText().toString().equals(""))
                {
                    quantity.setText("Please Key In Quantity!");
                    quantity.setTextColor(android.graphics.Color.RED);
                    quantity.setVisibility(View.VISIBLE);
                }
                else if(q.getText().toString().equals(""))
                {
                    reason.setVisibility(View.GONE);
                    quantity.setText("Please Key In Quantity!");
                    quantity.setTextColor(android.graphics.Color.RED);
                    quantity.setVisibility(View.VISIBLE);
                }
                else if(r.getText().toString().equals(""))
                {
                    quantity.setVisibility(View.GONE);
                    reason.setText("Please Key In Reason!");
                    reason.setTextColor(android.graphics.Color.RED);
                    reason.setVisibility(View.VISIBLE);
                }
                else
                {
                    Discrepancy discrepancy=new Discrepancy(s,user,itemID,description,q.getText().toString(),r.getText().toString(),status,now);
                    new AsyncTask<Discrepancy, Void, Void>() {
                        @Override
                        protected Void doInBackground(Discrepancy... params) {
                            Discrepancy.saveDiscrepancy(params[0]);
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void result) {
                            Toast.makeText(ItemDetailsActivity.this,"Updated Successfully!",Toast.LENGTH_LONG).show();
                        }
                    }.execute(discrepancy);
                    startActivity(new Intent(ItemDetailsActivity.this, ReportDiscrepancyActivity.class));
                }
            }
        });
    }
}
