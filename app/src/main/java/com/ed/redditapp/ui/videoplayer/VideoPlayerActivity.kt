package com.ed.redditapp.ui.videoplayer

import android.os.Bundle
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.databinding.ActivityVideoplayerBinding
import com.ed.redditapp.lib.api.video.VideoUrl
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class VideoPlayerActivity: BaseActivity<ActivityVideoplayerBinding>() {
    companion object {
        val EXTRA_VIDEO_URL = "extra_video_url"
    }

    val videoUrlProvider = VideoUrl()

    override fun getViewBinding() = ActivityVideoplayerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val player = SimpleExoPlayer.Builder(this).build()
        binding.player.player = player

        val videoUrl = videoUrlProvider.getUrl(intent.getStringExtra(EXTRA_VIDEO_URL)!!)
        player.setMediaItem(MediaItem.fromUri(videoUrl!!))
        player.prepare()
        player.play()
    }
}