package com.ed.redditapp.ui.search;

import com.ed.redditapp.AppDaggerComponent;
import com.ed.redditapp.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppDaggerComponent.class,
           modules = SearchDaggerModule.class)
public interface SearchDaggerComponent {
    void inject(SearchFragment fragment);
}
