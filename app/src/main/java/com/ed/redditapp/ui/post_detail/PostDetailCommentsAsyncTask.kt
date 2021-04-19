package com.ed.redditapp.ui.post_detail

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.AsyncTask.UIHandler
import com.ed.redditapp.lib.api.Comment
import com.ed.redditapp.lib.api.RedditApi

typealias PostDetailCommentsCallback = (comments: Array<Comment>) -> Unit

class PostDetailCommentsAsyncTask(private val redditApi: RedditApi) {
    fun run(postUrl: String, callback: PostDetailCommentsCallback) {
        AsyncTask().runAsync { h: UIHandler ->
            val comments: Array<Comment> = redditApi.getPostComments(postUrl)
            h.onUIThread { callback(comments) }
        }
    }
}