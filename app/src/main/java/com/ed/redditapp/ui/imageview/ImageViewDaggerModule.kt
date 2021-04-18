package com.ed.redditapp.ui.imageview

import com.ed.redditapp.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ImageViewDaggerModule(private val activity: ImageViewActivity) {
    @Provides
    @ActivityScope
    fun model() = ImageViewModel()

    @Provides
    @ActivityScope
    fun presenter(model: ImageViewModel) = ImageViewPresenter(activity, model)
}