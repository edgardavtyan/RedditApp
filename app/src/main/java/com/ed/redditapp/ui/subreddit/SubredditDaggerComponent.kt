package com.ed.redditapp.ui.subreddit

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppDaggerComponent::class],
           modules = [SubredditDaggerModule::class])
interface SubredditDaggerComponent {
    fun inject(activity: SubredditActivity?)
}