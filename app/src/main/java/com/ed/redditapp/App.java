package com.ed.redditapp;

import android.app.Application;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;

public class App extends Application {
    private AppDaggerComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BigImageViewer.initialize(GlideImageLoader.with(this));
    }

    public AppDaggerComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppDaggerComponent
                    .builder()
                    .build();
        }

        return appComponent;
    }
}
