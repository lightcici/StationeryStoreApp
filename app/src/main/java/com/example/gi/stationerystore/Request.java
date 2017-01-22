package com.example.gi.stationerystore;

import java.util.HashMap;

/**
 * Created by Gi on 22/1/2017.
 */

public class Request extends HashMap<String,String> {

final static String host = "http://192.168.0.196/StationeryStore/Service.svc";

public Request(String rqId, String date, String requester, String status, String comment) {
        put("rqId", rqId);
        put("date", date);
        put("requester", requester);
        put("status", status);
        put("comment", comment);

        }

public Request(){}
}
