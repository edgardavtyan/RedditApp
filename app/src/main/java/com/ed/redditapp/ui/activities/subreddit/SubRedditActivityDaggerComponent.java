package com.ed.redditapp.ui.activities.subreddit;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.AppDaggerComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDaggerComponent.class,
           modules = SubRedditDaggerModule.class)
public interface SubRedditActivityDaggerComponent {
    void inject(SubRedditActivity activity);
}
