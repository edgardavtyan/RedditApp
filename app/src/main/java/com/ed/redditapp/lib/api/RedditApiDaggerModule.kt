package com.ed.redditapp.lib.api

import com.ed.redditapp.lib.api.standard.StandardRedditApi
import com.ed.redditapp.lib.http.HttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RedditApiDaggerModule {
    @Provides @Singleton
    fun provideRedditApi(): RedditApi = StandardRedditApi(HttpClient())
}