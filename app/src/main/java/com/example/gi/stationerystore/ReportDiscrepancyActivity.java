package com.example.gi.stationerystore;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class ReportDiscrepancyActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button searchButton=(Button) findViewById(R.id.button2);
        final EditText category=(EditText) findViewById(R.id.editText2);
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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=category.getText().toString();
                new AsyncTask<String, Void, List<Item>>() {
                    @Override
                    protected List<Item> doInBackground(String... params) {
                        return Item.searchItemList(params[0]);
                    }
                    @Override
                    protected void onPostExecute(List<Item> result) {
                        SimpleAdapter adapter = new SimpleAdapter(ReportDiscrepancyActivity.this,result,R.layout.listing,
                                new String[]{"ItemID", "Description"},
                                new int[]{R.id.textViewTitle,R.id.textViewPrice});
                        setListAdapter(adapter);
                    }
                }.execute(s);
            }
        });
    }
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        Item item = (Item) getListAdapter().getItem(position);
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("ItemID", item.get("ItemID"));
        startActivity(intent);
    }
}
