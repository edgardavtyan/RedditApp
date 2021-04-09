package com.ed.redditapp.ui.post_detail;

import com.ed.redditapp.lib.api.RedditApi;

public class PostDetailModel {
    private final PostDetailCommentsAsyncTask commentsAsyncTask;

    public PostDetailModel(RedditApi redditApi) {
        commentsAsyncTask = new PostDetailCommentsAsyncTask(redditApi);
    }

    public void getPostComments(String postUrl, PostDetailCommentsAsyncTask.Callback callback) {
        commentsAsyncTask.run(postUrl, callback);
    }
}
