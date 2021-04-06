package com.ed.redditapp;

import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.RedditApiDaggerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RedditApiDaggerModule.class})
public interface AppDaggerComponent {
    RedditApi redditApi();
}
