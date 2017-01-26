package com.example.gi.stationerystore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gi on 22/1/2017.
 */

public class Discrepancy extends HashMap<String,String> {

    final static String host = "http://10.10.24.57/StationeryStore/Service.svc";

    public Discrepancy(String Description, String DiscrepancyId,String ItemCode,String Quantity,String Reason, String Status) {
        put("Description", Description);
        put("DiscrepancyId", DiscrepancyId);
        put("ItemCode", ItemCode);
        put("Quantity", Quantity);
        put("Reason", Reason);
        put("Status", Status);
    }

    public Discrepancy(){}

    public static String getMaxPrice(String itemID)
    {
        return JSONParser.getStream(host+"/GetMaxPrice/"+itemID);
    }
    public static String getMaxDiscrepancyId()
    {
        return  JSONParser.getStream(host+"GetMaxDiscrepancyId");
    }
}
