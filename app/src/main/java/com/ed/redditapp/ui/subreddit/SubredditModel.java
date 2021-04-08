package com.ed.redditapp.ui.subreddit;

import com.ed.redditapp.lib.api.RedditApi;

public class SubredditModel {
    private final SubredditInfoAsyncTask infoAsyncTask;
    private final SubredditPostsAsyncTask postsAsyncTask;

    public SubredditModel(RedditApi redditApi) {
        infoAsyncTask = new SubredditInfoAsyncTask(redditApi);
        postsAsyncTask = new SubredditPostsAsyncTask(redditApi);
    }

    public void getSubredditInfo(String subredditName, SubredditInfoAsyncTask.Callback callback) {
        infoAsyncTask.run(subredditName, callback);
    }

    public void getSubredditPosts(String subredditName, SubredditPostsAsyncTask.Callback callback) {
        postsAsyncTask.run(subredditName, callback);
    }
}
