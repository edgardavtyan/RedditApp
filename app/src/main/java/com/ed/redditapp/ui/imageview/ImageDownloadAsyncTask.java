package com.ed.redditapp.ui.imageview;

import android.graphics.Bitmap;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.http.HttpClient;

public class ImageDownloadAsyncTask {
    public interface Callback {
        void callback(Bitmap image);
    }

    private final HttpClient httpClient;

    public ImageDownloadAsyncTask() {
        httpClient = new HttpClient();
    }

    public void run(String url, Callback callback) {
        new AsyncTask().runAsync(h -> {
            Bitmap bitmap = httpClient.getBitmap(url);
            h.onUIThread(() -> callback.callback(bitmap));
        });
    }
}
