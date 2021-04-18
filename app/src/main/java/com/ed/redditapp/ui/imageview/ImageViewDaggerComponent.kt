package com.ed.redditapp.ui.imageview

import com.ed.redditapp.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [ImageViewDaggerModule::class])
interface ImageViewDaggerComponent {
    fun inject(activity: ImageViewActivity?)
}