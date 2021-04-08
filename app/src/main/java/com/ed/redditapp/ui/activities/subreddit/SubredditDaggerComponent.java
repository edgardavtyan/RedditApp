package com.ed.redditapp.ui.activities.subreddit;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.AppDaggerComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDaggerComponent.class,
           modules = SubredditDaggerModule.class)
public interface SubredditDaggerComponent {
    void inject(SubredditActivity activity);
}
