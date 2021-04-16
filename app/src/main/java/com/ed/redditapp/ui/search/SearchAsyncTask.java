package com.ed.redditapp.ui.search;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.Timer;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.SearchItemSubreddit;

public class SearchAsyncTask {
    public interface Callback {
        void callback(SearchItemSubreddit[] result);
    }

    private final Timer searchDelayTimer;

    private String searchText;
    private Callback searchCallback;

    public SearchAsyncTask(RedditApi api) {
        searchDelayTimer = new Timer(1000, () -> {
            new AsyncTask().runAsync(h -> {
                SearchItemSubreddit[] result = api.searchSubreddits(searchText);
                h.onUIThread(() -> searchCallback.callback(result));
            });
        });
    }

    public void run(String searchText, Callback searchCallback) {
        this.searchText = searchText;
        this.searchCallback = searchCallback;
        searchDelayTimer.run();
    }
}
