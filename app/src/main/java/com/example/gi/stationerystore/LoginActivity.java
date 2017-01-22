package com.example.gi.stationerystore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText e1 = (EditText)findViewById(R.id.editTextUsername);
        final EditText e2 = (EditText)findViewById(R.id.editTextPassword);

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                User user = new User();
                user.put("Username",e1.getText().toString());
                user.put("Password",e2.getText().toString());
                new AsyncTask<User, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(User... params) {
                        return User.authenticate(params[0]);
                    }
                    @Override
                    protected void onPostExecute(Boolean result) {
                        if(result == false){
                            TextView error = (TextView)findViewById(R.id.textViewError);
                            error.setText("Wrong UserName or Password. Please try again.");
                        }else if(result == true){
                            SharedPreferences pref =
                                    PreferenceManager.getDefaultSharedPreferences
                                            (getApplicationContext());
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("user", e1.getText().toString());
                            editor.commit();

                            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //startActivity(intent);

                        }
                    }
                }.execute(user);


            }
        });
    }



}
