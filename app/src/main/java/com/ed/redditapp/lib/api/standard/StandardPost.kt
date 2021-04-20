package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.MediaType
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
    override val postHint: String
    override val timestamp = json.getLong("created_utc")
    override val commentsCount = json.getInt("num_comments")
    override val points = json.getInt("ups")

    override val content: String?
    override val contentType: PostContentType?

    override val mediaType: MediaType
    override val mediaUrl: String?

    override var thumbnailSource: PostThumbnail? = null
    override var thumbnail320: PostThumbnail? = null
    override var thumbnail640: PostThumbnail? = null
    override var thumbnail960: PostThumbnail? = null

    init {
        if (json.has("post_hint")) {
            postHint = json.getString("post_hint")
        } else {
            postHint = "Text"
        }

        if (json.has("selftext_html")) {
            content = json.getString("selftext_html")
            contentType = PostContentType.TEXT
        } else {
            content = null
            contentType = null
        }

        when {
            domain.equals("v.redd.it") -> {
                mediaType = MediaType.VIDEO
                if (json.getString("media").equals("null")) {
                    mediaUrl = null
                } else {
                    mediaUrl = json.getJSONObject("media")
                            .getJSONObject("reddit_video")
                            .getString("fallback_url")
                }
            }
            else -> {
                mediaType = MediaType.NONE
                mediaUrl = null
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