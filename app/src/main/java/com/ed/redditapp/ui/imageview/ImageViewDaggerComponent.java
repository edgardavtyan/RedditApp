package com.ed.redditapp.ui.imageview;

import com.ed.redditapp.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ImageViewDaggerModule.class)
public interface ImageViewDaggerComponent {
    void inject(ImageViewActivity activity);
}
