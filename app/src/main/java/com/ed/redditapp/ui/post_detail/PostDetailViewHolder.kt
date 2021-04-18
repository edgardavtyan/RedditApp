package com.ed.redditapp.ui.post_detail

import androidx.recyclerview.widget.RecyclerView
import android.text.format.DateUtils
import com.ed.redditapp.R
import android.text.Html
import android.util.DisplayMetrics
import android.view.View
import com.ed.redditapp.databinding.ListitemCommentBinding
import com.ed.redditapp.format
import java.util.*

class PostDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListitemCommentBinding.bind(itemView)
    private val context = itemView.context

    fun setInfo(points: Int, timestamp: Long, username: String) {
        val niceDateStr = DateUtils.getRelativeTimeSpanString(
                timestamp * 1000,
                Calendar.getInstance().timeInMillis,
                DateUtils.MINUTE_IN_MILLIS).toString()
        val pattern = context.getString(R.string.comment_info_pattern)
        val info: String = pattern.format(points, niceDateStr, username)
        binding.user.text = Html.fromHtml(info)
    }

    fun setBody(body: String) {
        binding.body.setHtml(body)
    }

    fun setIndent(indent: Int) {
        val densityDpi = context.resources.displayMetrics.densityDpi
        val px = indent * 3 * densityDpi / DisplayMetrics.DENSITY_DEFAULT
        binding.root.translationX = px.toFloat()
    }
}