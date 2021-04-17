package com.ed.redditapp.lib.api

interface RedditApi {
    fun searchSubreddits(query: String): Array<SearchItemSubreddit>
    fun getSubredditInfo(subredditName: String): SubReddit
    fun getSubredditPosts(subredditName: String): Array<Post>
    fun getSubredditIconUrl(subreddit: String): String
    fun getPostComments(postUrl: String): Array<Comment>
}