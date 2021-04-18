package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.lib.api.Post

class SubredditPresenter(
        private val view: SubredditActivity,
        private val model: SubredditModel) {

    fun onActivityCreated(subredditName: String) {
        model.getSubredditInfo(subredditName, view::updateSubredditInfo)
        model.getSubredditPosts(subredditName, view::updatePosts)
    }

    fun onPostTitleClicked(post: Post) {
        view.gotoPostDetail(post)
    }

    fun onThumbnailClicked(post: Post) {
        view.gotoImageView(post.thumbnailSource!!.url)
    }
}