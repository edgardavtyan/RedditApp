package com.ed.redditapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        final String[] result = new String[1];
        executor.execute(() -> {
            try {
                String basicAuth = "Basic " + new String(Base64.encode("nryh78AW89RscQ:nopassword".getBytes(),Base64.NO_WRAP ));
                URL url = new URL("https://www.reddit.com/api/v1/access_token");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.addRequestProperty("Authorization", basicAuth);
                connection.addRequestProperty("Host", "www.reddit.com");
                connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("grant_type", "https://oauth.reddit.com/grants/installed_client")
                        .appendQueryParameter("device_id", "DO_NOT_TRACK_THIS_DEVICE");
                String query = builder.build().getEncodedQuery();

                byte[] queryBytes = query.getBytes();
                connection.addRequestProperty("Content-Length", String.valueOf(queryBytes.length));
                connection.getOutputStream().write(queryBytes);

                connection.connect();

                InputStream stream = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                String line = "";

                while ((line = reader.readLine()) != null)
                    result[0] += line;
            } catch (Exception e) {
                text.setText(e.getMessage());
            }
            handler.post(() -> {
                text.setText(result[0]);
            });
        });


    }
}