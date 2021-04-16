package com.ed.redditapp.lib.api;

import com.ed.redditapp.ui.postlist.Post;

public interface RedditApi {
    SubReddit[] searchSubreddits(String query);
    SubReddit getSubredditInfo(String subredditName);
    Post[] getSubredditPosts(String subredditName);
    String getSubredditIconUrl(String subreddit);
    Comment[] getPostComments(String postUrl);
}
