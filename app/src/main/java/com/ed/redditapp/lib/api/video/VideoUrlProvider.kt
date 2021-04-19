package com.ed.redditapp.lib.api.video

interface VideoUrlProvider {
    fun getVideoUrl(url: String): String
}