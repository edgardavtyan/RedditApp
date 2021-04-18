package com.ed.redditapp.ui.search

import com.ed.redditapp.FragmentScope
import com.ed.redditapp.lib.api.RedditApi
import dagger.Module
import dagger.Provides

@Module
class SearchDaggerModule(private val fragment: SearchFragment) {
    @Provides
    @FragmentScope
    fun provideModel(redditApi: RedditApi): SearchModel {
        return SearchModel(redditApi)
    }

    @Provides
    @FragmentScope
    fun providePresenter(model: SearchModel): SearchPresenter {
        return SearchPresenter(model, fragment)
    }

    @Provides
    @FragmentScope
    fun provideAdapter(presenter: SearchPresenter): SearchAdapter {
        return SearchAdapter(fragment.requireContext(), presenter)
    }
}