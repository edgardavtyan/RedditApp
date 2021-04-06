package com.ed.redditapp.ui.fragments.search;

import com.ed.redditapp.AppDaggerComponent;
import com.ed.redditapp.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDaggerComponent.class,
           modules = SearchFragmentDaggerModule.class)
public interface SearchFragmentDaggerComponent {
    void inject(SearchFragment fragment);
}
