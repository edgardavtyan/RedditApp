package com.ed.redditapp.lib.api;

import dagger.Module;
import dagger.Provides;

@Module
public class RedditApiDaggerModule {
    @Provides
    public RedditApi provideRedditApi() {
        return new RedditApi();
    }
}
