package com.example.gi.stationerystore;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class ReportDiscrepancyActivity extends Activity implements AdapterView.OnItemClickListener {
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing);

        searchButton=(Button) findViewById(R.id.button2);
        final EditText category=(EditText) findViewById(R.id.editText2);

        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... params) {
                return Item.getItemList();
            }
            @Override
            protected void onPostExecute(List<Item> result) {
                String[] a=new String[result.size()];
                for (int i=0;i<result.size();i++)
                {
                    a[i]=result.get(i).get("ItemID")+" "+result.get(i).get("Description");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportDiscrepancyActivity.this,
                        R.layout.row, R.id.textView1, a);
                ListView list = (ListView) findViewById(R.id.listView1);
                list.setAdapter(adapter);
                list.setOnItemClickListener(ReportDiscrepancyActivity.this);
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
                        String[] a=new String[result.size()];
                        for (int i=0;i<result.size();i++)
                        {
                            a[i]=result.get(i).get("ItemID")+" "+result.get(i).get("Description");
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportDiscrepancyActivity.this,
                                R.layout.row, R.id.textView1, a);
                        ListView list = (ListView) findViewById(R.id.listView1);
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(ReportDiscrepancyActivity.this);
                    }
                }.execute(s);
            }
        });
    }


@Override
    public void onItemClick(AdapterView<?> av, View v, int position, long id) {
        String item = (String) av.getAdapter().getItem(position);
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        String[] info=item.split(" ");
        intent.putExtra("ItemID", info[0]);
        intent.putExtra("Description", info[1]);
        startActivity(intent);
}
}
