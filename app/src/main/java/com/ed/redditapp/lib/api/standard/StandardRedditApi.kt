package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.*
import com.ed.redditapp.lib.http.HttpClient
import java.util.*

class StandardRedditApi : RedditApi {
    companion object {
        private const val URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on"
        private const val URL_SUBREDDIT_ABOUT = "https://www.reddit.com/r/%s/about.json?raw_json=1"
        private const val USL_SUBREDDIT_ROOT = "https://www.reddit.com/r/%s.json?raw_json=1&after=%s"
        private const val URL_COMMENTS = "https://www.reddit.com%s.json?raw_json=1"
    }

    private val httpClient = HttpClient()

    override fun searchSubreddits(query: String): Array<SearchItemSubreddit> {
        val url = String.format(URL_SEARCH_SUBREDDIT, query)
        val entries = httpClient.getJson(url).getJSONObject("data").getJSONArray("children")
        return Array(entries.length()) {
            val data = entries.getJSONObject(it).getJSONObject("data")
            StandardSearchItemSubreddit(data)
        }
    }

    override fun getSubredditInfo(subredditName: String): SubReddit {
        val url = URL_SUBREDDIT_ABOUT.format(subredditName)
        return StandardSubreddit(httpClient.getJson(url).getJSONObject("data"))
    }

    override fun getSubredditPosts(subredditName: String): Array<Post> {
        return getSubredditPosts(subredditName, null)
    }

    override fun getSubredditPosts(subredditName: String, after: String?): Array<Post> {
        var url = "https://www.reddit.com/r/$subredditName.json" +
                "?raw_json=1"

        if (after != null) {
            url += "&after=$after"
        }

        val jsonData = httpClient.getJson(url).getJSONObject("data")
        val postsJson = jsonData.getJSONArray("children")
        return Array(postsJson.length()) {
            val data = postsJson.getJSONObject(it).getJSONObject("data")
            StandardPost(data, jsonData.getString("after"))
        }
    }

    override fun getSubredditIconUrl(subreddit: String): String {
        val url = URL_SUBREDDIT_ABOUT.format(subreddit)
        val json = httpClient.getJson(url).getJSONObject("data")
        val iconUrl = json.getString("icon_img")
        val communityIcon = json.getString("community_icon")
        return if (iconUrl.isNotEmpty()) iconUrl else communityIcon
    }

    override fun getPostComments(postUrl: String): Array<Comment> {
        val url = String.format(URL_COMMENTS, postUrl)
        val commentsJson = httpClient
                .getArray(url)
                .getJSONObject(1)
                .getJSONObject("data")
                .getJSONArray("children")
        val jsonStack = Stack<StandardJsonComment>()
        val comments = ArrayList<Comment>()
        for (i in commentsJson.length() - 1 downTo 0) {
            if (commentsJson.getJSONObject(i).getString("kind") == "t1") {
                val jsonComment = commentsJson.getJSONObject(i).getJSONObject("data")
                jsonStack.push(StandardJsonComment(jsonComment, 0))
            }
        }

        while (jsonStack.size > 0) {
            val jsonComment = jsonStack.pop()
            val data = jsonComment.data
            comments.add(StandardComment(jsonComment))
            if (data.has("replies") && data["replies"] != "") {
                jsonComment.incrementIndent()
                val repliesJson = data
                        .getJSONObject("replies")
                        .getJSONObject("data")
                        .getJSONArray("children")
                for (i in repliesJson.length() - 1 downTo 0) {
                    val replyJson = repliesJson.getJSONObject(i)
                    if (replyJson.getString("kind") == "t1") {
                        jsonStack.push(StandardJsonComment(
                                replyJson.getJSONObject("data"), jsonComment.indent))
                    }
                }
            }
        }

        return comments.toTypedArray()
    }
}