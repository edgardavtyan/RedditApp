package com.ed.redditapp.ui.fragments.search;

import com.ed.redditapp.lib.api.RedditApi;

public class SearchModel {
    private final SearchAsyncTask searchAsyncTask;

    public SearchModel(RedditApi redditApi) {
        this.searchAsyncTask = new SearchAsyncTask(redditApi);
    }

    public void searchSubreddits(String searchText, SearchAsyncTask.Callback callback) {
        searchAsyncTask.run(searchText, callback);
    }
}
