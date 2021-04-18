package com.ed.redditapp.ui.post_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ed.redditapp.R
import com.ed.redditapp.lib.api.Comment

class PostDetailAdapter : RecyclerView.Adapter<PostDetailViewHolder>() {
    private var comments: Array<Comment> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.listitem_comment, parent, false)
        return PostDetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostDetailViewHolder, position: Int) {
        val comment = comments[position]
        holder.setInfo(comment.points, comment.timestamp, comment.username)
        holder.setBody(comment.body)
        holder.setIndent(comment.indent)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun updateComments(comments: Array<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }
}