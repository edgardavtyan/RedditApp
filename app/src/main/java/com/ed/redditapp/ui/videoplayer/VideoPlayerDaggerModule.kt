package com.ed.redditapp.ui.videoplayer

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.lib.api.video.VideoUrl
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.Module
import dagger.Provides

@Module
class VideoPlayerDaggerModule(private val activity: VideoPlayerActivity) {
    @Provides @ActivityScope
    fun videoUrlProvider() = VideoUrl()

    @Provides @ActivityScope
    fun model(videoUrlProvider: VideoUrl) = VideoPlayerModel(videoUrlProvider)

    @Provides @ActivityScope
    fun presenter(model: VideoPlayerModel) = VideoPlayerPresenter(activity, model)

    @Provides @ActivityScope
    fun exoPlayer() = SimpleExoPlayer.Builder(activity).build()
}