package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityDaggerModule {
    private final MainActivity activity;

    public MainActivityDaggerModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public PostListAdapter providePostListAdapter() {
        return new PostListAdapter();
    }

    @Provides
    @ActivityScope
    public MainActivityPresenter providePresenter(MainActivityModel model) {
        return new MainActivityPresenter(activity, model);
    }

    @Provides
    @ActivityScope
    public MainActivityModel provideModel(RedditApi redditApi) {
        return new MainActivityModel(redditApi);
    }
}
