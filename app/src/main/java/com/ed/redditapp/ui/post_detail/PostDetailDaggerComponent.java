package com.ed.redditapp.ui.post_detail;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.AppDaggerComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDaggerComponent.class,
           modules = PostDetailDaggerModule.class)
public interface PostDetailDaggerComponent {
    void inject(PostDetailActivity activity);
}
