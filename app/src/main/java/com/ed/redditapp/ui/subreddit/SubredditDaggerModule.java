package com.ed.redditapp.ui.subreddit;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.postlist.PostListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class SubredditDaggerModule {
    private final SubredditActivity activity;

    public SubredditDaggerModule(SubredditActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public SubredditModel provideModel(RedditApi redditApi) {
        return new SubredditModel(redditApi);
    }

    @Provides
    @ActivityScope
    public SubredditPresenter providePresenter(SubredditModel model) {
        return new SubredditPresenter(activity, model);
    }

    @Provides
    @ActivityScope
    public PostListAdapter provideAdapter() {
        return new PostListAdapter();
    }
}
