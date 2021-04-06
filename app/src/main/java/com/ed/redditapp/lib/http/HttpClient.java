package com.ed.redditapp.lib.http;

import android.net.Uri;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public String get(String url) {
        String result = null;

        try {
            URL urlConn = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConn.openConnection();
            connection.connect();
            result = getData(connection);
        } catch (Exception e) {
        }

        return result;
    }

    public String basicAuth(String url, String user, String pass, String[][] content) {
        String result = null;

        try {
            String basicAuth = "Basic " + new String(Base64.encode((user + ":" + pass).getBytes(), Base64.NO_WRAP));
            URL urlConn = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConn.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Authorization", basicAuth);
            connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            Uri.Builder builder = new Uri.Builder();
            for (String[] entry: content) builder.appendQueryParameter(entry[0], entry[1]);
            String query = builder.build().getEncodedQuery();

            byte[] queryBytes = query.getBytes();
            connection.addRequestProperty("Content-Length", String.valueOf(queryBytes.length));
            connection.getOutputStream().write(queryBytes);

            connection.connect();

            result = getData(connection);

            connection.disconnect();
        } catch (Exception e) {
        }

        return result;
    }

    public JSONObject getJson(String url) {
        try {
            return new JSONObject(get(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getData(HttpURLConnection connection) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream stream = new BufferedInputStream(connection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        while ((line = reader.readLine()) != null)
            builder.append(line);

        return builder.toString();
    }
}
