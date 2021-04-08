package com.ed.redditapp.ui.subreddit;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.Post;

public class SubredditPostsAsyncTask {
    private final RedditApi redditApi;

    public interface Callback {
        void callback(Post[] posts);
    }

    public SubredditPostsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(String subredditName, Callback callback) {
        new AsyncTask().runAsync(h -> {
            Post[] posts = redditApi.getSubredditPosts(subredditName);
            h.onUIThread(() -> callback.callback(posts));
        });
    }
}
