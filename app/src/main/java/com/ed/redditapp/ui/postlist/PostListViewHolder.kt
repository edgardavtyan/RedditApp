package com.ed.redditapp.ui.postlist

import android.text.Html
import android.text.format.DateUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ed.redditapp.R
import com.ed.redditapp.databinding.ListitemPostBinding
import com.ed.redditapp.lib.api.PostThumbnail
import java.util.*

class PostListViewHolder(private val binding : ListitemPostBinding)
        : RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context

    fun setTitle(title: String?) {
        binding.title.text = title
    }

    fun setInfo(points: Int, timeEpoch: Long, username: String) {
        val niceDateStr = DateUtils.getRelativeTimeSpanString(
                timeEpoch * 1000,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS).toString()
        val pattern = context.getString(R.string.post_info_pattern)
        val post = pattern.format(points, niceDateStr, username)
        binding.info.text = post
        binding.infoNoIcon.text = post
    }

    fun setSubreddit(subreddit: String?) {
        binding.subreddit.text = subreddit
    }

    fun setSubredditIcon(iconUrl: String?) {
        Glide.with(context)
                .load(iconUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.subredditIcon)
    }

    fun setCommentsCount(commentsCount: Int) {
        val pattern = context.getString(R.string.post_comments_pattern)
        binding.comments.text = pattern.format(commentsCount)
    }

    fun setThumbnail(thumbnail: PostThumbnail?) {
        if (thumbnail == null) {
            binding.thumbnail.visibility = View.GONE
            return
        } else {
            binding.thumbnail.visibility = View.VISIBLE
        }

        binding.thumbnail.post {
            val ratio = thumbnail.width.toDouble() / binding.thumbnail.width
            val height = Math.ceil(thumbnail.height / ratio).toInt()
            Glide.with(context)
                    .load(Html.fromHtml(thumbnail.url).toString())
                    .override(binding.root.width, height)
                    .into(binding.thumbnail)
        }
    }

    fun setDisplayIcon(isDisplayIcon: Boolean) {
        if (isDisplayIcon) {
            binding.topWrapper.visibility = View.VISIBLE
            binding.infoNoIcon.visibility = View.GONE
        } else {
            binding.topWrapper.visibility = View.GONE
            binding.infoNoIcon.visibility = View.VISIBLE
        }
    }

    fun setInfoClickListener(listener: View.OnClickListener?) {
        binding.topWrapper.setOnClickListener(listener)
    }

    fun setTitleClickListener(listener: View.OnClickListener?) {
        binding.title.setOnClickListener(listener)
    }

    fun setThumbnailClickListener(listener: View.OnClickListener?) {
        binding.thumbnail.setOnClickListener(listener)
    }
}