package com.ed.redditapp.ui.fragments.search;

import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.SubReddit;

import lombok.Getter;

public class SearchModel {
    private final SearchAsyncTask searchAsyncTask;

    @Getter
    private SubReddit[] subreddits;

    public SearchModel(RedditApi redditApi) {
        this.searchAsyncTask = new SearchAsyncTask(redditApi);
    }

    public void searchSubreddits(String searchText, SearchAsyncTask.Callback callback) {
        searchAsyncTask.run(searchText, subreddits -> {
            this.subreddits = subreddits;
            callback.callback(subreddits);
        });
    }
}
