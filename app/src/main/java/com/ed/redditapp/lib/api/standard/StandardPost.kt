package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.PostContentType
import com.ed.redditapp.lib.api.PostThumbnail
import org.json.JSONObject

class StandardPost(json: JSONObject, override val after: String) : Post() {
    override val title = json.getString("title")
    override val username = json.getString("author")
    override val subreddit = json.getString("subreddit")
    override val permalink = json.getString("permalink")
    override val domain = json.getString("domain")
    override val timestamp = json.getLong("created_utc")
    override val commentsCount = json.getInt("num_comments")
    override val points = json.getInt("ups")

    override val content: String
    override val contentType: PostContentType

    override var thumbnailSource: PostThumbnail? = null
    override var thumbnail320: PostThumbnail? = null
    override var thumbnail640: PostThumbnail? = null
    override var thumbnail960: PostThumbnail? = null

    init {
        val postHint = if (json.has("post_hint")) json.getString("post_hint") else null

        when {
            json.has("selftext_html") -> {
                content = json.getString("selftext_html")
                contentType = PostContentType.TEXT
            }
            postHint == "image" -> {
                content = json.getString("url")
                contentType = PostContentType.IMAGE
            }
            postHint == "link" -> {
                content = json.getString("url")
                contentType = PostContentType.LINK
            }
            postHint == "hosted:video" -> {
                content = json
                        .getJSONObject("media")
                        .getJSONObject("reddit_video")
                        .getString("fallback_url")
                contentType = PostContentType.VIDEO_HOSTED
            }
            postHint == "rich:video" -> {
                content = json.getString("url")
                contentType = PostContentType.VIDEO_RICH
            }
            else -> {
                content = json.getString("url")
                contentType = PostContentType.OTHER
            }
        }

        if (json.has("preview")) {
            thumbnailSource = PostThumbnail(json
                    .getJSONObject("preview")
                    .getJSONArray("images")
                    .getJSONObject(0)
                    .getJSONObject("source"))

            val thumbsJson = json
                    .getJSONObject("preview")
                    .getJSONArray("images")
                    .getJSONObject(0)
                    .getJSONArray("resolutions")

            if (thumbsJson.length() >= 3) {
                thumbnail320 = PostThumbnail(thumbsJson.getJSONObject(2))
            }
            if (thumbsJson.length() >= 4) {
                thumbnail640 = PostThumbnail(thumbsJson.getJSONObject(3))
            }
            if (thumbsJson.length() >= 5) {
                thumbnail960 = PostThumbnail(thumbsJson.getJSONObject(4))
            }
        }
    }
}