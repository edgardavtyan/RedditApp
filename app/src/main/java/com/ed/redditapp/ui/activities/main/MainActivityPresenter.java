package com.ed.redditapp.ui.activities.main;

public class MainActivityPresenter {
    private final MainActivity view;
    private final MainActivityModel model;

    public MainActivityPresenter(MainActivity view, MainActivityModel model) {
        this.view = view;
        this.model = model;
    }

    public void onActivityLoaded() {
        model.getMainPagePosts((posts) -> {
            view.updatePosts(posts);
            model.getMainPageSubredditIcons(view::updateSubredditIcons);
        });
    }
}
