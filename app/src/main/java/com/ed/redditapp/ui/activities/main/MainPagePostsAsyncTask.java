package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListItem;

public class MainPagePostsAsyncTask {
    public interface Callback {
        void callback(PostListItem[] posts);
    }

    private final RedditApi redditApi;

    public MainPagePostsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(Callback callback) {
        new AsyncTask().runAsync((h) -> {
            PostListItem[] posts = redditApi.getMainPagePosts();
            h.onUIThread(() -> callback.callback(posts));
        });
    }
}
