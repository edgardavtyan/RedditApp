package com.ed.redditapp

import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.lib.api.RedditApiDaggerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RedditApiDaggerModule::class])
interface AppDaggerComponent {
    fun redditApi(): RedditApi
}