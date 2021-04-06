package com.ed.redditapp.ui.fragments.search;

import com.ed.redditapp.FragmentScope;
import com.ed.redditapp.lib.api.RedditApi;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchFragmentDaggerModule {
    private final SearchFragment fragment;

    public SearchFragmentDaggerModule(SearchFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public SearchModel provideModel(RedditApi redditApi) {
        return new SearchModel(redditApi);
    }

    @Provides
    @FragmentScope
    public SearchPresenter providePresenter(SearchModel model) {
        return new SearchPresenter(model, fragment);
    }

    @Provides
    @FragmentScope
    SearchAdapter provideAdapter(SearchPresenter presenter) {
        return new SearchAdapter(fragment.getActivity(), presenter);
    }
}
