package com.ed.redditapp.ui.activities.subreddit;

import com.ed.redditapp.lib.api.RedditApi;

public class SubRedditModel {
    private final SubredditInfoAsyncTask infoAsyncTask;

    public SubRedditModel(RedditApi redditApi) {
        this.infoAsyncTask = new SubredditInfoAsyncTask(redditApi);
    }

    public void getSubredditInfo(String subredditName, SubredditInfoAsyncTask.Callback callback) {
        infoAsyncTask.run(subredditName, callback);
    }
}
