package com.ed.redditapp.lib.api;

import androidx.annotation.Nullable;

import com.ed.redditapp.lib.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class RedditApi {
    private static final String URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on";

    private final HttpClient httpClient;

    public RedditApi() {
        httpClient = new HttpClient();
    }

    @Nullable
    public SubReddit[] searchSubreddits(String query) {
        try {
            String url = String.format(URL_SEARCH_SUBREDDIT, query);
            JSONArray entries = httpClient.getJson(url).getJSONObject("data").getJSONArray("children");
            SubReddit[] results = new SubReddit[entries.length()];
            for (int i = 0; i < entries.length(); i++) {
                SubReddit subReddit = new SubReddit();
                JSONObject data = entries.getJSONObject(i).getJSONObject("data");
                subReddit.setName(data.getString("display_name"));
                subReddit.setSubsCount(data.getInt("subscribers"));
                results[i] = subReddit;
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }
}
