package com.ed.redditapp.ui.imageview;

import com.ed.redditapp.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageViewDaggerModule {
    private final ImageViewActivity activity;

    public ImageViewDaggerModule(ImageViewActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public ImageViewModel provideModel() {
        return new ImageViewModel();
    }

    @Provides
    @ActivityScope
    public ImageViewPresenter providePresenter(ImageViewModel model) {
        return new ImageViewPresenter(activity, model);
    }
}
