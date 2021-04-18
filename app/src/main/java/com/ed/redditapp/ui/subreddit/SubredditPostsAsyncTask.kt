package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

typealias SubredditPostsCallback = (posts: Array<Post>) -> Unit

class SubredditPostsAsyncTask(private val redditApi: RedditApi) {
    fun run(subredditName: String, callback: SubredditPostsCallback) {
        AsyncTask().runAsync { h ->
            val posts: Array<Post> = redditApi.getSubredditPosts(subredditName)
            h.onUIThread { callback(posts) }
        }
    }
}