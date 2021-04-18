package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.RedditApi

class MainModel(redditApi: RedditApi) {
    private var posts: Array<Post> = emptyArray()

    val mainPostsAsyncTask = MainPostsAsyncTask(redditApi)
    val subredditIconUrlAsyncTask = MainSubredditIconUrlAsyncTask(redditApi)

    fun getMainPagePosts(callback: (posts: Array<Post>) -> Unit) {
        mainPostsAsyncTask.run { posts ->
            this.posts = posts
            callback(posts)
        }
    }

    fun getMainPageSubredditIcons(callback: (String, Int) -> Unit) {
        subredditIconUrlAsyncTask.run(posts, callback)
    }
}