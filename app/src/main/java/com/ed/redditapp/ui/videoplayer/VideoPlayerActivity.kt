package com.ed.redditapp.ui.videoplayer

import android.os.Bundle
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.databinding.ActivityVideoplayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import javax.inject.Inject

class VideoPlayerActivity: BaseActivity<ActivityVideoplayerBinding>() {
    companion object {
        const val EXTRA_VIDEO_URL = "extra_video_url"
    }

    @Inject lateinit var presenter: VideoPlayerPresenter
    @Inject lateinit var exoplayer: SimpleExoPlayer

    override fun getViewBinding() = ActivityVideoplayerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerVideoPlayerDaggerComponent
                .builder()
                .videoPlayerDaggerModule(VideoPlayerDaggerModule(this))
                .build()
                .inject(this)

        binding.player.player = exoplayer

        presenter.onLoaded(intent.getStringExtra(EXTRA_VIDEO_URL)!!)

    }

    fun showVideo(videoUrl: String?) {
        if (videoUrl == null) return

        exoplayer.setMediaItem(MediaItem.fromUri(videoUrl))
        exoplayer.prepare()
        exoplayer.play()
    }
}