package com.ed.redditapp.ui.activities.subreddit;

public class SubRedditPresenter {
    private final SubRedditActivity view;
    private final SubRedditModel model;

    public SubRedditPresenter(SubRedditActivity view, SubRedditModel model) {
        this.view = view;
        this.model = model;
    }

    public void onActivityCreated(String subredditName) {
        model.getSubredditInfo(subredditName, view::updateSubredditInfo);
        model.getSubredditPosts(subredditName, view::updatePosts);
    }
}
