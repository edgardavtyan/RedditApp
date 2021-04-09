package com.ed.redditapp.ui.post_detail;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.lib.api.RedditApi;

import dagger.Module;
import dagger.Provides;

@Module
public class PostDetailDaggerModule {
    private final PostDetailActivity activity;

    public PostDetailDaggerModule(PostDetailActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public PostDetailAdapter provideAdapter() {
        return new PostDetailAdapter();
    }

    @Provides
    @ActivityScope
    public PostDetailModel provideModel(RedditApi redditApi) {
        return new PostDetailModel(redditApi);
    }

    @Provides
    @ActivityScope
    public PostDetailPresenter providePresenter(PostDetailModel model) {
        return new PostDetailPresenter(activity, model);
    }
}
