package com.example.gi.stationerystore;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.List;

public class ItemDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        String itemID = getIntent().getExtras().getString("ItemID");
        String description=getIntent().getExtras().getString("Description");

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

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                new AsyncTask<String, Void, List<Item>>() {
//                    @Override
//                    protected List<Item> doInBackground(String... params) {
//                        return Item.searchItemList(params[0]);
//                    }
//                    @Override
//                    protected void onPostExecute(List<Item> result) {
//                        SimpleAdapter adapter = new SimpleAdapter(ItemDetailsActivity.this,result,R.layout.listing,
//                                new String[]{"ItemID", "Description"},
//                                new int[]{R.id.textViewTitle,R.id.textViewPrice});
//                        setListAdapter(adapter);
//                    }
//                }.execute(s);
//            }
//        });
    }
}
