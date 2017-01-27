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
        EditText r=(EditText) findViewById(R.id.editText4);
        EditText q=(EditText) findViewById(R.id.editText);
        Date n = new Date(System.currentTimeMillis());
        final String now=n.toString();
        final String status="Pending Approval";
        final String reason=r.getText().toString();
        final String quantity=q.getText().toString();

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
                discId.getText();
                String[] a= discId.getText().toString().split("\"");
                int b=Integer.parseInt( a[0]);
                b=b+1;
                String discrepancyid= String.valueOf(b);
                Discrepancy discrepancy=new Discrepancy(discrepancyid,user,itemID,description,quantity,reason,status,now);
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
        });
    }
}
