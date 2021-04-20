package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.PostContentType

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
        if (post.contentType == PostContentType.IMAGE) {
            view.gotoImageView(post.thumbnailSource!!.url)
        }

        if (post.contentType == PostContentType.VIDEO_HOSTED) {
            view.gotoVideoView(post.content)
        }
    }
}