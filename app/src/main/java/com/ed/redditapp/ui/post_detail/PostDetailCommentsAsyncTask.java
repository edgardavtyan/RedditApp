package com.ed.redditapp.ui.post_detail;

import com.ed.redditapp.lib.AsyncTask;
import com.ed.redditapp.lib.api.Comment;
import com.ed.redditapp.lib.api.RedditApi;

public class PostDetailCommentsAsyncTask {
    public interface Callback {
        void callback(Comment[] comments);
    }

    private final RedditApi redditApi;

    public PostDetailCommentsAsyncTask(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public void run(String postUrl, Callback callback) {
        new AsyncTask().runAsync(h -> {
            Comment[] comments = redditApi.getPostComments(postUrl);
            h.onUIThread(() -> callback.callback(comments));
        });
    }
}
