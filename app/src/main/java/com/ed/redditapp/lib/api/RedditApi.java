package com.ed.redditapp.lib.api;

import androidx.annotation.Nullable;

import com.ed.redditapp.lib.http.HttpClient;

import org.json.JSONArray;

public class RedditApi {
    private static final String URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on";

    private final HttpClient httpClient;

    public RedditApi() {
        httpClient = new HttpClient();
    }

    @Nullable
    public String[] searchSubreddits(String query) {
        try {
            String url = String.format(URL_SEARCH_SUBREDDIT, query);
            JSONArray entries = httpClient.getJson(url).getJSONObject("data").getJSONArray("children");
            String[] results = new String[entries.length()];
            for (int i = 0; i < entries.length(); i++) {
                results[i] = entries.getJSONObject(i).getJSONObject("data").getString("display_name");
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }
}
