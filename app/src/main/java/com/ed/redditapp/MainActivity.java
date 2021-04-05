package com.ed.redditapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ed.redditapp.lib.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);

        HttpClient httpClient = new HttpClient();

        final String[] result = new String[1];
        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                String url = "https://www.reddit.com/api/v1/access_token";
//                String user = "nryh78AW89RscQ";
//                String password = "nopassword";
//                String[][] content = {
//                        {"grant_type", "https://oauth.reddit.com/grants/installed_client"},
//                        {"device_id", "DO_NOT_TRACK_THIS_DEVICE"}};
//                result[0] = httpClient.basicAuth(url, user, password, content);
//            } catch (Exception e) {
//                text.setText(e.getMessage());
//            }
//
//            handler.post(() -> text.setText(result[0]));
//        });

        executor.execute(() -> {
            String url = "https://www.reddit.com/subreddits/search.json?q=people&include_over_18=on";
            String rawJson = httpClient.get(url);

            ArrayList<String> data = new ArrayList<>();
            try {
                JSONObject root = new JSONObject(rawJson);
                JSONArray children = root.getJSONObject("data").getJSONArray("children");
                for (int i = 0; i < children.length(); i++) {
                    JSONObject entry = children.getJSONObject(i);
                    data.add(entry.getJSONObject("data").getString("display_name"));
                }
            } catch (Exception e) {
            }

            handler.post(() -> text.setText(data.toString()));
        });
    }
}