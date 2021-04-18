package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.lib.api.RedditApi

class SubredditModel(redditApi: RedditApi) {
    private val infoAsyncTask = SubredditInfoAsyncTask(redditApi)
    private val postsAsyncTask = SubredditPostsAsyncTask(redditApi)

    fun getSubredditInfo(subredditName: String, callback: SubredditInfoCallback) {
        infoAsyncTask.run(subredditName, callback)
    }

    fun getSubredditPosts(subredditName: String, callback: SubredditPostsCallback) {
        postsAsyncTask.run(subredditName, callback)
    }

}