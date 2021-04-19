package com.ed.redditapp

import android.app.Activity
import android.content.Intent
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.ui.imageview.ImageViewActivity
import com.ed.redditapp.ui.post_detail.PostDetailActivity
import com.ed.redditapp.ui.subreddit.SubredditActivity

class Navigator {
    fun gotoSubreddit(activity: Activity, subredditName: String?) {
        val intent = Intent(activity, SubredditActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(SubredditActivity.EXTRA_SUBREDDIT_NAME, subredditName)
        activity.startActivity(intent)
    }

    fun gotoPostDetail(activity: Activity, post: Post) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(PostDetailActivity.EXTRA_POST_URL, post.permalink)
        activity.startActivity(intent)
    }

    fun gotoImageView(activity: Activity, url: String?) {
        val intent = Intent(activity, ImageViewActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(ImageViewActivity.EXTRA_IMAGE_URL, url)
        activity.startActivity(intent)
    }
}