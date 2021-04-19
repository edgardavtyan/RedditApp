package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.ui.postlist.PostListAdapter
import dagger.Module
import dagger.Provides

@Module
class SubredditDaggerModule(private val activity: SubredditActivity) {
    @Provides @ActivityScope
    fun model(redditApi: RedditApi) = SubredditModel(redditApi)

    @Provides @ActivityScope
    fun presenter(model: SubredditModel) = SubredditPresenter(activity, model)

    @Provides @ActivityScope
    fun adapter() = PostListAdapter()
}