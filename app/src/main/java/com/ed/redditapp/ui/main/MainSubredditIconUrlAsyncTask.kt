package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

class MainSubredditIconUrlAsyncTask(private val redditApi: RedditApi) {
    fun run(posts: Array<Post>, callback: (iconUrl: String, position: Int) -> Unit) {
        for (i in IntRange(0, posts.size)) {
            AsyncTask().runAsync { h ->
                val iconUrl = redditApi.getSubredditIconUrl(posts[i].subreddit)
                h.onUIThread { callback(iconUrl, i) }
            }
        }
    }
}