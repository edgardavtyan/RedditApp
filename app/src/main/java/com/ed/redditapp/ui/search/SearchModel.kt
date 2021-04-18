package com.ed.redditapp.ui.search

import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.lib.api.SearchItemSubreddit
import lombok.Getter

class SearchModel(redditApi: RedditApi?) {
    private val searchAsyncTask = SearchAsyncTask(redditApi!!)

    var subreddits: Array<SearchItemSubreddit> = emptyArray()
        private set

    fun searchSubreddits(searchText: String, callback: SearchAsyncCallback) {
        searchAsyncTask.run(searchText) { subreddits ->
            this.subreddits = subreddits
            callback(subreddits)
        }
    }
}