package com.example.gi.stationerystore;

import java.util.HashMap;

/**
 * Created by Gi on 22/1/2017.
 */

public class Discrepancy extends HashMap<String,String> {

    final static String host = "http://192.168.0.196/StationeryStore/Service.svc";

    public Discrepancy(String Description, String DiscrepancyId,String ItemCode,String Quantity,String Reason, String Status) {
        put("Description", Description);
        put("DiscrepancyId", DiscrepancyId);
        put("ItemCode", ItemCode);
        put("Quantity", Quantity);
        put("Reason", Reason);
        put("Status", Status);

    }

    public Discrepancy(){}

}
