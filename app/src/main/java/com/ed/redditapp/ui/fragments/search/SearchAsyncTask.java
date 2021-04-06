package com.ed.redditapp.ui.fragments.search;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.Timer;
import com.ed.redditapp.lib.api.RedditApi;

public class SearchAsyncTask {
    public interface SearchResultCallback {
        void callback(String[] result);
    }

    private final Timer searchDelayTimer;

    private String searchText;
    private SearchResultCallback searchCallback;

    public SearchAsyncTask(RedditApi api) {
        searchDelayTimer = new Timer(1000, () -> {
            new AsyncTask().runAsync(h -> {
                String[] result = api.searchSubreddits(searchText);
                h.onUIThread(() -> searchCallback.callback(result));
            });
        });
    }

    public void run(String searchText, SearchResultCallback searchCallback) {
        this.searchText = searchText;
        this.searchCallback = searchCallback;
        searchDelayTimer.run();
    }
}
