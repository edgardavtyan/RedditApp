package com.ed.redditapp.ui.post_detail

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.AsyncTask.UIHandler
import com.ed.redditapp.lib.api.Comment
import com.ed.redditapp.lib.api.RedditApi

class PostDetailCommentsAsyncTask(private val redditApi: RedditApi) {
    fun run(postUrl: String?, callback: (comments: Array<Comment>) -> Unit) {
        AsyncTask().runAsync { h: UIHandler ->
            val comments: Array<Comment> = redditApi.getPostComments(postUrl!!)
            h.onUIThread { callback(comments) }
        }
    }
}