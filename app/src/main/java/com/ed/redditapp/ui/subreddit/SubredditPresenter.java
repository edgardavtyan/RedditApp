package com.ed.redditapp.ui.subreddit;

public class SubredditPresenter {
    private final SubredditActivity view;
    private final SubredditModel model;

    public SubredditPresenter(SubredditActivity view, SubredditModel model) {
        this.view = view;
        this.model = model;
    }

    public void onActivityCreated(String subredditName) {
        model.getSubredditInfo(subredditName, view::updateSubredditInfo);
        model.getSubredditPosts(subredditName, view::updatePosts);
    }
}
