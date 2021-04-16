package com.ed.redditapp.lib.api;

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
