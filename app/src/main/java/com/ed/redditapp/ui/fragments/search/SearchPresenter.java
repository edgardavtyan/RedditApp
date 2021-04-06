package com.ed.redditapp.ui.fragments.search;

import android.view.View;

public class SearchPresenter {
    private final SearchModel model;
    private final SearchFragment view;

    public SearchPresenter(SearchModel model, SearchFragment view) {
        this.model = model;
        this.view = view;
    }

    public void onSearchTextChanged(String searchText) {
        model.searchSubreddits(searchText, view::updateSearchResults);
    }

    public void onBtnCloseClick(View v) {
        view.close();
    }
}
