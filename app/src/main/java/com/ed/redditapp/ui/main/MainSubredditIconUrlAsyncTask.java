package com.ed.redditapp.ui.main;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.RedditApi;

public class MainSubredditIconUrlAsyncTask {
    interface Callback {
        void callback(String iconUrl, int position);
    }

    private final RedditApi redditApi;

    public MainSubredditIconUrlAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(Post[] posts, Callback callback) {
        for (int i = 0; i < posts.length; i++) {
            int[] index = {i};
            Post post = posts[i];
            new AsyncTask().runAsync((h) -> {
                String iconUrl = redditApi.getSubredditIconUrl(post.getSubreddit());
                h.onUIThread(() -> callback.callback(iconUrl, index[0]));
            });
        }
    }
}
