package com.example.gi.stationerystore;

import java.util.HashMap;

/**
 * Created by Gi on 22/1/2017.
 */

public class RequestDetail extends HashMap<String,String> {

    final static String host = "http://192.168.0.196/StationeryStore/Service.svc";

    public RequestDetail(String RequestId, String ItemId, String RequestDate, String Description, String UOM,String RequestQty,String Status,String Comment) {
        put("RequestId", RequestId);
        put("ItemId", ItemId);
        put("RequestDate", RequestDate);
        put("Description", Description);
        put("UOM", UOM);
        put("RequestQty", RequestQty);
        put("Status", Status);
        put("Comment", Comment);

    }

    public RequestDetail(){}
}
