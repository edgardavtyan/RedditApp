package com.ed.redditapp.lib.api.video

class RedditVideoProvider: VideoUrlProvider {
    override fun getVideoUrl(url: String): String {
        return url
    }
}