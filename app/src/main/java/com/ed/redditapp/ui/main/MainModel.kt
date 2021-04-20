package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

class MainModel(redditApi: RedditApi) {
    private var posts: Array<Post> = emptyArray()
    private val mainPostsAsyncTask = MainPostsAsyncTask(redditApi)
    private val subredditIconUrlAsyncTask = MainSubredditIconUrlAsyncTask(redditApi)

    fun getMainPagePosts(callback: MainPostsCallback) {
        mainPostsAsyncTask.run(null) { posts ->
            this.posts = posts
            callback(posts)
        }
    }

    fun getNextPosts(after: String, callback: MainPostsCallback) {
        mainPostsAsyncTask.run(after) { posts ->
            this.posts += posts
            callback(posts)
        }
    }

    fun getMainPageSubredditIcons(callback: MainSubredditIconUrlCallback) {
        subredditIconUrlAsyncTask.run(posts, callback)
    }
}