package com.ed.redditapp.lib.api.video

class VideoUrl {
    val redditVideoProvider = RedditVideoProvider()

    fun getUrl(url: String): String? {
        if (url.startsWith("https://v.redd")) {
            return redditVideoProvider.getVideoUrl(url);
        }

        return null
    }
}