package com.ed.redditapp.lib.api

import com.ed.redditapp.lib.api.kotlin.KotlinRedditApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RedditApiDaggerModule {
    @Provides @Singleton
    fun provideRedditApi(): RedditApi = KotlinRedditApi()
}