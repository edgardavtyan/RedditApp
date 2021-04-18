package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.lib.api.SubReddit

typealias SubredditInfoCallback = (result: SubReddit) -> Unit

class SubredditInfoAsyncTask(private val redditApi: RedditApi) {
    fun run(subredditName: String, callback: SubredditInfoCallback) {
        AsyncTask().runAsync { h ->
            val subreddit = redditApi.getSubredditInfo(subredditName)
            h.onUIThread { callback(subreddit) }
        }
    }
}