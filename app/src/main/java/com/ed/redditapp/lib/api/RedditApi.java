package com.ed.redditapp.lib.api;

public interface RedditApi {
    SearchItemSubreddit[] searchSubreddits(String query);
    SubReddit getSubredditInfo(String subredditName);
    Post[] getSubredditPosts(String subredditName);
    String getSubredditIconUrl(String subreddit);
    Comment[] getPostComments(String postUrl);
}
