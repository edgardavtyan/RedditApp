package com.ed.redditapp;

import android.app.Application;

public class App extends Application {
    private AppDaggerComponent appComponent;

    public AppDaggerComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppDaggerComponent
                    .builder()
                    .build();
        }

        return appComponent;
    }
}
