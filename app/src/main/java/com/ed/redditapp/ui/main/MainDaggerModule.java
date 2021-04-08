package com.ed.redditapp.ui.main;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainDaggerModule {
    private final MainActivity activity;

    public MainDaggerModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public PostListAdapter providePostListAdapter() {
        return new PostListAdapter();
    }

    @Provides
    @ActivityScope
    public MainPresenter providePresenter(MainModel model) {
        return new MainPresenter(activity, model);
    }

    @Provides
    @ActivityScope
    public MainModel provideModel(RedditApi redditApi) {
        return new MainModel(redditApi);
    }
}
