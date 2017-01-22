package com.example.gi.stationerystore;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gi on 21/12/2016.
 */

public class User extends HashMap<String,String>{

    final static String host = "http://192.168.0.196/StationeryStore/Service.svc";

    public User(String username, String password) {
        put("Username", username);
        put("Password", password);

    }

    public User(){}


    public static boolean authenticate(User user) {
        JSONObject juser = new JSONObject();
        try {
            juser.put("Username", user.get("Username"));
            juser.put("Password", user.get("Password"));

        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"/Authenticate", juser.toString());

        Log.e("updateResult", result);
        return Boolean.parseBoolean(result);
    }


}
