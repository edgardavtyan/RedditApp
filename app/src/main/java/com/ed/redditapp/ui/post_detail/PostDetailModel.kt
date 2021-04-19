package com.ed.redditapp.ui.post_detail

import com.ed.redditapp.lib.api.RedditApi

class PostDetailModel(redditApi: RedditApi) {
    private val commentsAsyncTask = PostDetailCommentsAsyncTask(redditApi)

    fun getPostComments(postUrl: String, callback: PostDetailCommentsCallback) {
        commentsAsyncTask.run(postUrl, callback)
    }
}