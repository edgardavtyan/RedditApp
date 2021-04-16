package com.ed.redditapp.ui.main;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.RedditApi;

public class MainPostsAsyncTask {
    public interface Callback {
        void callback(Post[] posts);
    }

    private final RedditApi redditApi;

    public MainPostsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(Callback callback) {
        new AsyncTask().runAsync((h) -> {
            Post[] posts = redditApi.getSubredditPosts("popular");
            h.onUIThread(() -> callback.callback(posts));
        });
    }
}
