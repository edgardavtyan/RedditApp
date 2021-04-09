package com.ed.redditapp.ui.post_detail;

public class PostDetailPresenter {
    private final PostDetailActivity view;
    private final PostDetailModel model;

    public PostDetailPresenter(PostDetailActivity view, PostDetailModel model) {
        this.view = view;
        this.model = model;
    }

    public void onActivityLoaded(String postUrl) {
        model.getPostComments(postUrl, view::updateComments);
    }
}
