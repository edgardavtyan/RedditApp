package com.ed.redditapp.ui.search

import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.AsyncTask.UIHandler
import com.ed.redditapp.lib.Timer
import com.ed.redditapp.lib.api.RedditApi
import com.ed.redditapp.lib.api.SearchItemSubreddit

typealias SearchAsyncCallback = (result: Array<SearchItemSubreddit>) -> Unit

class SearchAsyncTask(api: RedditApi) {
    private val searchDelayTimer = Timer(1000) {
        AsyncTask().runAsync { h: UIHandler ->
            val result = api.searchSubreddits(searchText!!)
            h.onUIThread { searchCallback!!(result) }
        }
    }

    private var searchText: String? = null
    private var searchCallback: SearchAsyncCallback? = null

    fun run(searchText: String, callback: SearchAsyncCallback?) {
        this.searchText = searchText
        this.searchCallback = callback
        searchDelayTimer.run()
    }
}