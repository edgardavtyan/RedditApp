package com.ed.redditapp.lib.api.kotlin

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.PostThumbnail
import lombok.Getter
import lombok.Setter
import org.json.JSONObject

class KotlinPost(json: JSONObject) : Post() {
    override val title: String = json.getString("title")
    override val username: String = json.getString("author")
    override val subreddit: String = json.getString("subreddit")
    override val permalink: String = json.getString("permalink")
    override val timestamp: Long = json.getLong("created_utc")
    override val commentsCount: Int = json.getInt("num_comments")
    override val points: Int = json.getInt("ups")
    override var thumbnailSource: PostThumbnail? = null
    override var thumbnail320: PostThumbnail? = null
    override var thumbnail640: PostThumbnail? = null
    override var thumbnail960: PostThumbnail? = null

    init {
        if (json.has("previews")) {
            val previews = json.getJSONObject("previews")
            if (previews.has("source"))
                thumbnailSource = PostThumbnail(previews.getJSONObject("source"))
            if (previews.has("preview320"))
                thumbnail320 = PostThumbnail(previews.getJSONObject("preview320"))
            if (previews.has("preview640"))
                thumbnail640 = PostThumbnail(previews.getJSONObject("preview640"))
            if (previews.has("preview960"))
                thumbnail960 = PostThumbnail(previews.getJSONObject("preview960"))
        }
    }
}