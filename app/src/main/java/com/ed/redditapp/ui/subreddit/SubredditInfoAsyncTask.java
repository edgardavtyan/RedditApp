package com.ed.redditapp.ui.subreddit;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.SubReddit;

public class SubredditInfoAsyncTask {
    public interface Callback {
        void callback(SubReddit result);
    }

    private final RedditApi redditApi;

    public SubredditInfoAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(String subredditName, Callback callback) {
        new AsyncTask().runAsync((h) -> {
            SubReddit subreddit = redditApi.getSubredditInfo(subredditName);
            h.onUIThread(() -> callback.callback(subreddit));
        });
    }
}
