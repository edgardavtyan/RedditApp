package com.ed.redditapp

import android.app.Application
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader

class App : Application() {
    var appComponent: AppDaggerComponent? = null
        get() {
            if (field == null) {
                field = DaggerAppDaggerComponent
                        .builder()
                        .appDaggerModule(AppDaggerModule())
                        .build()
            }
            return field
        }
        private set

    override fun onCreate() {
        super.onCreate()
        BigImageViewer.initialize(GlideImageLoader.with(this))
    }
}