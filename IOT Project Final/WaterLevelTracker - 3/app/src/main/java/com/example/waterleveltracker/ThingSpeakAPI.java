package com.example.waterleveltracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThingSpeakAPI {
    private static final String BASE_URL = "https://api.thingspeak.com/channels/2567002/fields/1.json?api_key=E22HA6FAGXK1XU0N&results=1";
    private static final String CHANNEL_ID = "YOUR_CHANNEL_ID";
    //private static final String READ_API_KEY = "YOUR_READ_API_KEY";

    public static String getLatestData() throws Exception {
        //String urlString = BASE_URL + CHANNEL_ID + "/feeds.json?api_key=" + READ_API_KEY + "&results=1";

        String urlString = BASE_URL;
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();
        } finally {
            urlConnection.disconnect();
        }
    }
}
