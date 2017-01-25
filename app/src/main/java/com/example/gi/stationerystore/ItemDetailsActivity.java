package com.example.gi.stationerystore;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class ItemDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        String itemID=getIntent().getExtras().getString("ItemID");

//        new AsyncTask<String, Void, Item>() {
//            @Override
//            protected Item doInBackground(String... params) {
//                return Discrepancy.(params[0]);
//            }
//            @Override
//            protected void onPostExecute(Item result) {
//                for (int i=0; i<view.length; i++) {
//
//                    EditText t = (EditText) findViewById(view[i]);
//                    t.setText(result.get(key[i]));
//                }
//                Spinner category = (Spinner)findViewById(R.id.category);
//                category.setSelection(adapter.getPosition(result.get("Category")));
//            }
//        }.execute(itemID);
    }
}
