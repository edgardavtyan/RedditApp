package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListItem;

public class MainPageSubredditIconUrlAsyncTask {
    interface Callback {
        void callback(String iconUrl, int position);
    }

    private final RedditApi redditApi;

    public MainPageSubredditIconUrlAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(PostListItem[] posts, Callback callback) {
        for (int i = 0; i < posts.length; i++) {
            int[] index = {i};
            PostListItem post = posts[i];
            new AsyncTask().runAsync((h) -> {
                String iconUrl = redditApi.getSubredditIconUrl(post.getSubreddit());
                h.onUIThread(() -> callback.callback(iconUrl, index[0]));
            });
        }
    }
}
