package com.example.gi.stationerystore;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.List;

public class ReportDiscrepancyActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_discrepancy);

        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... params) {
                return Item.getItemList();
            }
            @Override
            protected void onPostExecute(List<Item> result) {
                SimpleAdapter adapter = new SimpleAdapter(ReportDiscrepancyActivity.this,result,R.layout.listing,
                        new String[]{"ItemID", "Description"},
                        new int[]{R.id.textViewTitle,R.id.textViewPrice});
                setListAdapter(adapter);
            }
        }.execute();
    }
}
