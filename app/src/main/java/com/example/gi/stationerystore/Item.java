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

    public static List<Item> getItemList()
    {
        List<Item> list = new ArrayList<Item>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/ItemList");
            for(int n = 0; n < a.length(); n++)
            {JSONObject c = a.getJSONObject(n);
                Item item = new Item(c.getString("ItemID"),
                        c.getString("Category"),
                        c.getString("Description"),
                        c.getString("ReorderLevel"),
                        c.getString("InStock"),
                        c.getString("GUOM"));
                list.add(item);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Item> searchItemList(String info)
    {
        List<Item> list = new ArrayList<Item>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/SearchItemList/"+info);
            for(int n = 0; n < a.length(); n++)
            {JSONObject c = a.getJSONObject(n);
                Item item = new Item(c.getString("ItemID"),
                        c.getString("Category"),
                        c.getString("Description"),
                        c.getString("ReorderLevel"),
                        c.getString("InStock"),
                        c.getString("GUOM"));
                list.add(item);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
