package com.ed.redditapp.ui.activities.subreddit;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListItem;

public class SubredditPostsAsyncTask {
    private final RedditApi redditApi;

    public interface Callback {
        void callback(PostListItem[] posts);
    }

    public SubredditPostsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(String subredditName, Callback callback) {
        new AsyncTask().runAsync(h -> {
            PostListItem[] posts = redditApi.getSubredditPosts(subredditName);
            h.onUIThread(() -> callback.callback(posts));
        });
    }
}
