package com.ed.redditapp.lib.api;

import android.text.Html;

import androidx.annotation.Nullable;

import com.ed.redditapp.lib.http.HttpClient;
import com.ed.redditapp.ui.postlist.Post;

import org.json.JSONArray;
import org.json.JSONObject;

public class RedditApi {
    private static final String URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on";
    private static final String URL_SUBREDDIT = "https://www.reddit.com/r/%s/about.json";
    private static final String URL_MAIN_PAGE = "https://www.reddit.com/r/popular.json";
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

    public SubReddit getSubredditInfo(String subredditName) {
        try {
            String url = String.format(URL_SUBREDDIT, subredditName);
            JSONObject json = httpClient.getJson(url).getJSONObject("data");

            SubReddit subreddit = new SubReddit();
            subreddit.setName(json.getString("display_name"));
            subreddit.setSubsCount(json.getInt("subscribers"));
            subreddit.setTitle(json.getString("title"));
            subreddit.setDescription(json.getString("public_description"));
            return subreddit;
        } catch (Exception e) {
            return null;
        }
    }

    public Post[] getMainPagePosts() {
        try {
            JSONArray postsJson = httpClient
                    .getJson(URL_MAIN_PAGE)
                    .getJSONObject("data")
                    .getJSONArray("children");

            Post[] posts = new Post[postsJson.length()];
            for (int i = 0; i < postsJson.length(); i++) {
                JSONObject postJson = postsJson.getJSONObject(i).getJSONObject("data");
                Post post = new Post();
                post.setTitle(postJson.getString("title"));
                post.setUsername(postJson.getString("author"));
                post.setCommentsCount(postJson.getInt("num_comments"));
                post.setPoints(postJson.getInt("ups"));
                post.setTimestamp(postJson.getLong("created_utc"));

                if (postJson.has("preview")) {
                    JSONArray thumbsJson = postJson
                            .getJSONObject("preview")
                            .getJSONArray("images")
                            .getJSONObject(0)
                            .getJSONArray("resolutions");

                    if (thumbsJson.length() < 3) {
                        post.setThumbnailSmallUrl(postJson.getString("url"));
                    }

                    if (thumbsJson.length() >= 4) {
                        post.setThumbnail320Url(Html.fromHtml(thumbsJson.getJSONObject(2).getString("url")).toString());
                    }

                    if (thumbsJson.length() >= 5) {
                        post.setThumbnail640Url(Html.fromHtml(thumbsJson.getJSONObject(3).getString("url")).toString());
                    }

                    if (thumbsJson.length() >= 6) {
                        post.setThumbnail960Url(Html.fromHtml(thumbsJson.getJSONObject(4).getString("url")).toString());
                    }
                }

                posts[i] = post;
            }

            return posts;
        } catch (Exception e) {
            return null;
        }
    }
}
