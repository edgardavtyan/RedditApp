package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

typealias MainPostsCallback = (posts: Array<Post>) -> Unit

class MainPostsAsyncTask(val redditApi: RedditApi) {
    fun run(callback: MainPostsCallback) {
        AsyncTask().runAsync { h ->
            val posts = redditApi.getSubredditPosts("popular")
            h.onUIThread { callback(posts) }
        }
    }
}