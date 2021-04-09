package com.ed.redditapp;

import android.app.Activity;
import android.content.Intent;

import com.ed.redditapp.ui.post_detail.PostDetailActivity;
import com.ed.redditapp.ui.postlist.Post;
import com.ed.redditapp.ui.subreddit.SubredditActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Navigator {
    public static void gotoSubreddit(Activity activity, String subredditName) {
        Intent intent = new Intent(activity, SubredditActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SubredditActivity.EXTRA_SUBREDDIT_NAME, subredditName);
        activity.startActivity(intent);
    }

    public static void gotoPostDetail(Activity activity, Post post) {
        Intent intent = new Intent(activity, PostDetailActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PostDetailActivity.EXTRA_POST_URL, post.getPermalink());
        activity.startActivity(intent);
    }
}
