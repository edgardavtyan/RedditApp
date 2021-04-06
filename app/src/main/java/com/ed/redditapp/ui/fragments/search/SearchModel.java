package com.ed.redditapp.ui.fragments.search;

import android.os.Handler;
import android.os.Looper;

import com.ed.redditapp.lib.Timer;
import com.ed.redditapp.lib.api.RedditApi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchModel {
    public interface SearchResultCallback {
        void callback(String[] result);
    }

    private RedditApi redditApi;

    public SearchModel(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    private String searchText;
    private SearchResultCallback searchCallback;

    private final Timer searchDelayTimer = new Timer(1000, () -> {
        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            String[] result = redditApi.searchSubreddits(searchText);
            handler.post(() -> searchCallback.callback(result));
        });
    });

    public void searchSubreddits(String searchText, SearchResultCallback callback) {
        this.searchText = searchText;
        this.searchCallback = callback;
        searchDelayTimer.run();
    }
}
