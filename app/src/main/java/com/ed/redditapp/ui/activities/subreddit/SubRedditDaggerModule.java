package com.ed.redditapp.ui.activities.subreddit;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.lib.api.RedditApi;

import dagger.Module;
import dagger.Provides;

@Module
public class SubRedditDaggerModule {
    private final SubRedditActivity activity;

    public SubRedditDaggerModule(SubRedditActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public SubRedditModel provideModel(RedditApi redditApi) {
        return new SubRedditModel(redditApi);
    }

    @Provides
    @ActivityScope
    public SubRedditPresenter providePresenter(SubRedditModel model) {
        return new SubRedditPresenter(activity, model);
    }
}
