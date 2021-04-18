package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

class MainPostsAsyncTask(val redditApi: RedditApi) {
    fun run(callback: (posts: Array<Post>) -> Unit) {
        AsyncTask().runAsync { h ->
            val posts = redditApi.getSubredditPosts("popular")
            h.onUIThread { callback(posts) }
        }
    }
}