package com.ed.redditapp.ui.videoplayer

import com.ed.redditapp.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [VideoPlayerDaggerModule::class])
interface VideoPlayerDaggerComponent {
    fun inject(activity: VideoPlayerActivity)
}