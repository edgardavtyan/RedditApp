package com.ed.redditapp.lib.api.custom;

import com.ed.redditapp.lib.api.Comment;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.SearchItemSubreddit;
import com.ed.redditapp.lib.api.SubReddit;
import com.ed.redditapp.lib.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomRedditApi implements RedditApi {
    private static final String URL_ROOT = "http://192.168.1.100:3000";
    private static final String URL_SUBREDDIT = URL_ROOT + "/r/%s";
    private static final String URL_SUBREDDIT_ABOUT = URL_ROOT + "/about/%s";
    private static final String URL_SEARCH = URL_ROOT + "/search/%s";
    private static final String URL_COMMENTS = URL_ROOT + "/comments?permalink=%s";

    private final HttpClient httpClient;

    public CustomRedditApi() {
        httpClient = new HttpClient();
    }

    @Override
    public SearchItemSubreddit[] searchSubreddits(String query) {
        try {
            JSONArray searchJson = httpClient.getArray(String.format(URL_SEARCH, query));
            SearchItemSubreddit[] subreddits = new SearchItemSubreddit[searchJson.length()];
            for (int i = 0; i < searchJson.length(); i++) {
                subreddits[i] = new CustomSearchItemSubreddit(searchJson.getJSONObject(i));
            }
            return subreddits;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public SubReddit getSubredditInfo(String subredditName) {
        try {
            String url = String.format(URL_SUBREDDIT_ABOUT, subredditName);
            return new CustomSubreddit(httpClient.getJson(url));
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public Post[] getSubredditPosts(String subredditName) {
        try {
            JSONObject json = httpClient.getJson(String.format(URL_SUBREDDIT, subredditName));
            JSONArray postsJsonArray = json.getJSONArray("posts");
            Post[] posts = new Post[postsJsonArray.length()];
            for (int i = 0; i < postsJsonArray.length(); i++) {
                posts[i] = new CustomPost(postsJsonArray.getJSONObject(i));
            }

            return posts;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSubredditIconUrl(String subreddit) {
        try {
            JSONObject aboutJson = httpClient.getJson(String.format(URL_SUBREDDIT_ABOUT, subreddit));
            return aboutJson.getString("icon");
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public Comment[] getPostComments(String postUrl) {
        try {
            JSONArray commentsJson = httpClient.getArray(String.format(URL_COMMENTS, postUrl));
            Comment[] comments = new Comment[commentsJson.length()];
            for (int i = 0; i < commentsJson.length(); i++) {
                comments[i] = new CustomComment(commentsJson.getJSONObject(i));
            }
            return comments;
        } catch (JSONException e) {
            return null;
        }
    }
}
