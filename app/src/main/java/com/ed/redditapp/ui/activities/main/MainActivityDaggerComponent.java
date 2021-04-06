package com.ed.redditapp.ui.activities.main;

import com.ed.redditapp.ActivityScope;
import com.ed.redditapp.AppDaggerComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppDaggerComponent.class)
public interface MainActivityDaggerComponent {
    void inject(MainActivity activity);
}