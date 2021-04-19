package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.PostThumbnail
import org.json.JSONObject

class StandardPost(json: JSONObject) : Post() {
    override val title = json.getString("title")
    override val username = json.getString("author")
    override val subreddit = json.getString("subreddit")
    override val permalink = json.getString("permalink")
    override val timestamp = json.getLong("created_utc")
    override val commentsCount = json.getInt("num_comments")
    override val points = json.getInt("ups")

    override var thumbnailSource: PostThumbnail? = null
    override var thumbnail320: PostThumbnail? = null
    override var thumbnail640: PostThumbnail? = null
    override var thumbnail960: PostThumbnail? = null

    init {
        if (json.has("preview")) {
            val thumbsJson = json
                    .getJSONObject("preview")
                    .getJSONArray("images")
                    .getJSONObject(0)
                    .getJSONArray("resolutions")
            if (thumbsJson.length() < 3) {
                val thumbSourceJson = json
                        .getJSONObject("preview")
                        .getJSONArray("images")
                        .getJSONObject(0)
                        .getJSONObject("source")
                thumbnailSource = PostThumbnail(thumbSourceJson)
            }
            if (thumbsJson.length() >= 3) {
                thumbnail320 = PostThumbnail(thumbsJson.getJSONObject(3))
            }
            if (thumbsJson.length() >= 4) {
                thumbnail640 = PostThumbnail(thumbsJson.getJSONObject(4))
            }
            if (thumbsJson.length() >= 5) {
                thumbnail960 = PostThumbnail(thumbsJson.getJSONObject(4))
            }
        }
    }
}