package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.api.Post

class MainModel(
        val postsAsyncTask: MainPostsAsyncTask,
        val subredditIconUrlAsyncTask: MainSubredditIconUrlAsyncTask) {

    private var posts: Array<Post> = emptyArray()

    fun getMainPagePosts(callback: MainPostsCallback) {
        postsAsyncTask.run(null) { posts ->
            this.posts = posts
            callback(posts)
        }
    }

    fun getNextPosts(after: String, callback: MainPostsCallback) {
        postsAsyncTask.run(after) { posts ->
            this.posts += posts
            callback(posts)
        }
    }

    fun getMainPageSubredditIcons(callback: MainSubredditIconUrlCallback) {
        subredditIconUrlAsyncTask.run(posts, callback)
    }
}