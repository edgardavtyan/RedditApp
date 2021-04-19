package com.ed.redditapp

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDaggerModule {
    @Provides @Singleton
    fun navigator() = Navigator()
}