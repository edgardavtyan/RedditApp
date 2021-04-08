package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.ui.postlist.PostListItem;

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

    public void onPostInfoClicked(PostListItem post) {
        view.gotoSubreddit(post.getSubreddit());
    }
}
