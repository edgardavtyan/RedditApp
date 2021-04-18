package com.ed.redditapp.ui.post_detail

class PostDetailPresenter(
        private val view: PostDetailActivity,
        private val model: PostDetailModel) {
    fun onActivityLoaded(postUrl: String) {
        model.getPostComments(postUrl, view::updateComments)
    }
}