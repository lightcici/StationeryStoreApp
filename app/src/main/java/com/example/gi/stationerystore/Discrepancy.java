package com.example.gi.stationerystore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gi on 22/1/2017.
 */

public class Discrepancy extends HashMap<String,String> {

    final static String host = "http://10.10.24.57/StationeryStore/Service.svc";

    public Discrepancy(String DiscrepancyId, String StaffId, String ItemCode,String Description, String Quantity, String Reason, String Status,String Now) {
        put("DiscrepancyId", DiscrepancyId);
        put("StaffId", StaffId);
        put("ItemCode", ItemCode);
        put("Description",Description);
        put("Quantity", Quantity);
        put("Reason", Reason);
        put("Status", Status);
        put("Now",Now);
    }

    public Discrepancy(){}

    public static String getMaxPrice(String itemID)
    {
        return JSONParser.getStream(host+"/GetMaxPrice/"+itemID);
    }
    public static String getMaxDiscrepancyId()
    {
        return  JSONParser.getStream(host+"/GetMaxDiscrepancyId");
    }
    public static void saveDiscrepancy(Discrepancy discrepancy)
    {
        JSONObject jdiscrepancy = new JSONObject();
        try {
            jdiscrepancy.put("DiscrepancyId", discrepancy.get("DiscrepancyId"));
            jdiscrepancy.put("StaffId", discrepancy.get("StaffId"));
            jdiscrepancy.put("ItemCode", discrepancy.get("ItemCode"));
            jdiscrepancy.put("Description", discrepancy.get("Description"));
            jdiscrepancy.put("Quantity", discrepancy.get("Quantity"));
            jdiscrepancy.put("Reason", discrepancy.get("Reason"));
            jdiscrepancy.put("Status", discrepancy.get("Status"));
            jdiscrepancy.put("Now", discrepancy.get("Now"));
        } catch (Exception e) {
        }
        JSONParser.postStream(host+"/SaveDiscrepancy",jdiscrepancy.toString());
    }
}
