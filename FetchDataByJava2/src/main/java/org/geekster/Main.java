package org.geekster;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlStr = "https://api.zippopotam.us/us/33162";
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;

        try{
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            System.out.println("problem in url");
        }

        connection = (HttpURLConnection) url.openConnection();
        responseCode = connection.getResponseCode();

        if(responseCode == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder data = new StringBuilder();
            String readData = null;

            while ((readData = in.readLine())!=null){
                data.append(readData);
            }

            in.close();

            JSONObject jsonResponse = new JSONObject(data.toString());
            System.out.println("post code " + ": " + jsonResponse.get("post code"));
            System.out.println("country " + ": " + jsonResponse.get("country"));
            System.out.println("country abbreviation " + ": " + jsonResponse.get("country abbreviation"));
            System.out.println("places " + ": " + jsonResponse.get("places"));
        }

    }
}