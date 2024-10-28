package com.example.waterleveltracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThingSpeakData {
    private int waterLevel;

    public ThingSpeakData(String jsonResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray feeds = jsonObject.getJSONArray("feeds");
        if (feeds.length() > 0) {
            JSONObject latestFeed = feeds.getJSONObject(0);
            this.waterLevel = latestFeed.getInt("field1");

        }
    }


    public int getWaterLevel(){
        return waterLevel;
    }
}