package com.ed.redditapp.lib.api;

import com.ed.redditapp.lib.api.custom.CustomRedditApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RedditApiDaggerModule {
    @Provides
    @Singleton
    public RedditApi provideRedditApi() {
        return new CustomRedditApi();
    }
}
