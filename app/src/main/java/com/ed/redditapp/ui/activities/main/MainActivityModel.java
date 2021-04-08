package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListItem;

public class MainActivityModel {
    private final MainPagePostsAsyncTask mainPagePostsAsyncTask;
    private final MainPageSubredditIconUrlAsyncTask subredditIconUrlAsyncTask;

    private PostListItem[] posts;

    public MainActivityModel(RedditApi redditApi) {
        this.mainPagePostsAsyncTask = new MainPagePostsAsyncTask(redditApi);
        this.subredditIconUrlAsyncTask = new MainPageSubredditIconUrlAsyncTask(redditApi);
    }

    public void getMainPagePosts(MainPagePostsAsyncTask.Callback callback) {
        mainPagePostsAsyncTask.run((posts) -> {
            this.posts = posts;
            callback.callback(posts);
        });
    }

    public void getMainPageSubredditIcons(MainPageSubredditIconUrlAsyncTask.Callback callback) {
        subredditIconUrlAsyncTask.run(posts, callback);
    }
}
