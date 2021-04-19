package com.ed.redditapp.ui.post_detail

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.lib.api.RedditApi
import dagger.Module
import dagger.Provides

@Module
class PostDetailDaggerModule(private val activity: PostDetailActivity) {
    @Provides @ActivityScope
    fun provideAdapter() = PostDetailAdapter()

    @Provides @ActivityScope
    fun provideModel(redditApi: RedditApi) = PostDetailModel(redditApi)

    @Provides @ActivityScope
    fun providePresenter(model: PostDetailModel) =
        PostDetailPresenter(activity, model)
}