package com.ed.redditapp.ui.search

import android.view.View

class SearchPresenter(private val model: SearchModel, private val view: SearchFragment) {
    fun onSearchTextChanged(searchText: String) {
        model.searchSubreddits(searchText, view::updateSearchResults)
    }

    fun onBtnCloseClick(v: View?) {
        view.close()
    }

    fun onItemClick(position: Int) {
        view.gotoSubRedditActivity(model.subreddits[position])
    }
}