package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.lib.api.RedditApi;

public class MainActivityModel {
    private final MainPagePostsAsyncTask mainPagePostsAsyncTask;

    public MainActivityModel(RedditApi redditApi) {
        this.mainPagePostsAsyncTask = new MainPagePostsAsyncTask(redditApi);
    }

    public void getMainPagePosts(MainPagePostsAsyncTask.Callback callback) {
        mainPagePostsAsyncTask.run(callback);
    }
}
