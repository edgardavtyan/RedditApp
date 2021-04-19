package com.ed.redditapp.lib.api

import com.ed.redditapp.lib.api.standard.StandardRedditApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RedditApiDaggerModule {
    @Provides @Singleton
    fun provideRedditApi(): RedditApi = StandardRedditApi()
}