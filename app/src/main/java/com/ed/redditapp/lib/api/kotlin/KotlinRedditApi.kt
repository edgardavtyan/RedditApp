package com.ed.redditapp.lib.api.kotlin

import com.ed.redditapp.lib.api.*
import com.ed.redditapp.lib.http.HttpClient

class KotlinRedditApi : RedditApi {
    private val URL_ROOT = "http://192.168.1.100:3000"

    private val httpClient: HttpClient = HttpClient()

    override fun searchSubreddits(query: String): Array<SearchItemSubreddit> {
        val json = httpClient.getArray("$URL_ROOT/search/$query")
        return Array(json.length()) {
            KotlinSearchItemSubreddit(json.getJSONObject(it))}
    }

    override fun getSubredditInfo(subredditName: String): SubReddit {
        val url = "$URL_ROOT/about/$subredditName"
        return KotlinSubreddit(httpClient.getJson(url))
    }

    override fun getSubredditPosts(subredditName: String): Array<Post> {
        val url = "$URL_ROOT/r/$subredditName"
        val json = httpClient
                .getJson(url)
                .getJSONArray("posts")
        return Array(json.length()) {KotlinPost(json.getJSONObject(it))}
    }

    override fun getSubredditPosts(subredditName: String, after: String?): Array<Post> {
        TODO("Not yet implemented")
    }

    override fun getSubredditIconUrl(subreddit: String): String {
        return getSubredditInfo(subreddit).iconUrl
    }

    override fun getPostComments(postUrl: String): Array<Comment> {
        val json = httpClient.getArray("$URL_ROOT/comments?permalink=$postUrl")
        return Array(json.length()) {KotlinComment(json.getJSONObject(it))}

    }
}