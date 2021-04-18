package com.ed.redditapp.ui.main

import com.ed.redditapp.ActivityScope
import com.ed.redditapp.AppDaggerComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppDaggerComponent::class],
           modules = [MainDaggerModule::class])
interface MainDaggerComponent {
    fun inject(activity: MainActivity)
}