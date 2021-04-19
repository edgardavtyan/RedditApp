package com.ed.redditapp.ui.main

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.ui.postlist.PostListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainDaggerModule(val acitivty: MainActivity) {
    @Provides @ActivityScope
    fun adapter() = PostListAdapter()

    @Provides @ActivityScope
    fun presenter(model: MainModel) = MainPresenter(acitivty, model)

    @Provides @ActivityScope
    fun model(redditApi: RedditApi) = MainModel(redditApi)
}