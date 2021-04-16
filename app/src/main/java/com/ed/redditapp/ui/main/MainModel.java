package com.ed.redditapp.ui.main;

import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.RedditApi;

public class MainModel {
    private final MainPostsAsyncTask mainPostsAsyncTask;
    private final MainSubredditIconUrlAsyncTask subredditIconUrlAsyncTask;

    private Post[] posts;

    public MainModel(RedditApi redditApi) {
        this.mainPostsAsyncTask = new MainPostsAsyncTask(redditApi);
        this.subredditIconUrlAsyncTask = new MainSubredditIconUrlAsyncTask(redditApi);
    }

    public void getMainPagePosts(MainPostsAsyncTask.Callback callback) {
        mainPostsAsyncTask.run((posts) -> {
            this.posts = posts;
            callback.callback(posts);
        });
    }

    public void getMainPageSubredditIcons(MainSubredditIconUrlAsyncTask.Callback callback) {
        subredditIconUrlAsyncTask.run(posts, callback);
    }
}
