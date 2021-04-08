package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.Post;

public class MainPagePostsAsyncTask {
    public interface Callback {
        void callback(Post[] posts);
    }

    private final RedditApi redditApi;

    public MainPagePostsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(Callback callback) {
        new AsyncTask().runAsync((h) -> {
            Post[] posts = redditApi.getSubredditPosts("popular");
            h.onUIThread(() -> callback.callback(posts));
        });
    }
}
