package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.api.Post

class MainPresenter(val view: MainActivity, val model: MainModel) {
    fun onActivityLoaded() {
        model.getMainPagePosts { posts ->
            view.updatePosts(posts)
            model.getMainPageSubredditIcons(view::updateSubredditIcons)
        }
    }

    fun onPostInfoClicked(post: Post) {
        view.gotoSubreddit(post.subreddit)
    }

    fun onPostTitleClicked(post: Post) {
        view.gotoPostDetail(post)
    }

    fun onThumbnailClicked(post: Post) {
        view.gotoImageView(post)
    }

    fun onNearEndReached(post: Post) {
        model.getNextPosts(post.after) { posts ->
            view.addNextPosts(posts)
        }
    }
}