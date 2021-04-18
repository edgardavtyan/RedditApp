package com.ed.redditapp.ui.post_detail

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.lib.api.RedditApi
import dagger.Module
import dagger.Provides

@Module
class PostDetailDaggerModule(private val activity: PostDetailActivity) {
    @Provides
    @ActivityScope
    fun provideAdapter(): PostDetailAdapter {
        return PostDetailAdapter()
    }

    @Provides
    @ActivityScope
    fun provideModel(redditApi: RedditApi): PostDetailModel {
        return PostDetailModel(redditApi)
    }

    @Provides
    @ActivityScope
    fun providePresenter(model: PostDetailModel): PostDetailPresenter {
        return PostDetailPresenter(activity, model)
    }
}