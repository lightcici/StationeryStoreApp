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

public class Item extends HashMap<String,String>{

    final static String host = "http://192.168.0.196/StationeryStore/Service.svc";

    public Item(String Category, String Description, String UOM, String InStock, String ItemID, String ReorderLevel) {
        put("Category", Category);
        put("Description", Description);
        put("UOM", UOM);
        put("InStock", InStock);
        put("ItemID", ItemID);
        put("ReorderLevel", ReorderLevel);
    }

    public Item(){}



}
