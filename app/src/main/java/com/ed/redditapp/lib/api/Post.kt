package com.ed.redditapp.lib.api

enum class MediaType {
    IMAGE,
    VREDDIT,
    NONE,
}

abstract class Post {

    abstract val title: String
    abstract val username: String
    abstract val subreddit: String
    abstract val permalink: String
    abstract val thumbnailSource: PostThumbnail?
    abstract val thumbnail320: PostThumbnail?
    abstract val thumbnail640: PostThumbnail?
    abstract val thumbnail960: PostThumbnail?
    abstract val timestamp: Long
    abstract val commentsCount: Int
    abstract val points: Int

    abstract val mediaType: MediaType
    abstract val mediaUrl: String?

    var subredditIconUrl: String? = null

    val largestThumbnail: PostThumbnail?
        get() {
            if (thumbnail960 != null) return thumbnail960
            if (thumbnail640 != null) return thumbnail640
            if (thumbnail320 != null) return thumbnail320
            return if (thumbnailSource != null) thumbnailSource else null
        }
}
