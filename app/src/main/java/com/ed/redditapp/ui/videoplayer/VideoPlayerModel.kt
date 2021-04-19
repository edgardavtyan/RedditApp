package com.ed.redditapp.ui.videoplayer

import com.ed.redditapp.lib.api.video.VideoUrl

class VideoPlayerModel(private val videoUrlProvider: VideoUrl) {
    fun getVideoUrl(url: String): String? {
        return videoUrlProvider.getUrl(url)
    }
}