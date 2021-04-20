package com.ed.redditapp.ui.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ed.redditapp.R
import com.ed.redditapp.lib.api.Post

typealias PostListClickListener = (post: Post) -> Unit
typealias PostListNearEndListener = (post: Post) -> Unit

class PostListAdapter: RecyclerView.Adapter<PostListViewHolder>() {
    private val VIEW_TYPE_FIRST = 1
    private val VIEW_TYPE_DEFAULT = 0
    private val PAYLOAD_ICON_CHANGED = 1

    private var posts: Array<Post> = emptyArray()

    var infoClickListener: PostListClickListener? = null
    var titleClickListener: PostListClickListener? = null
    var thumbnailClickListener: PostListClickListener? = null
    var nearEndListener: PostListNearEndListener? = null

    var isDisplayIcon: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.listitem_post, parent, false)
        return PostListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val post = posts[position]
        holder.setTitle(post.title)
        holder.setCommentsCount(post.commentsCount)
        holder.setInfo(post.points, post.timestamp, post.username)
        holder.setThumbnail(post.largestThumbnail)
        holder.setSubreddit(post.subreddit)
        holder.setSubredditIcon(post.subredditIconUrl)
        holder.setDisplayIcon(isDisplayIcon)
        holder.setInfoClickListener { infoClickListener?.invoke(post) }
        holder.setTitleClickListener { titleClickListener?.invoke(post) }
        holder.setThumbnailClickListener { thumbnailClickListener?.invoke(post) }

        if (position == posts.size - 8) {
            nearEndListener?.invoke(post)
        }
    }

    override fun onBindViewHolder(
            holder: PostListViewHolder,
            position: Int,
            payloads: MutableList<Any>) {
        if (payloads.size == 0) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.setSubredditIcon(posts[position].subredditIconUrl)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_FIRST else VIEW_TYPE_DEFAULT
    }

    fun updateData(posts: Array<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    fun updateIcon(iconUrl: String, position: Int) {
        posts[position].subredditIconUrl = iconUrl
        notifyItemChanged(position, PAYLOAD_ICON_CHANGED)
    }

    fun addPosts(newPosts: Array<Post>) {
        val positionBefore = posts.size - 1
        posts += newPosts
        notifyItemRangeInserted(positionBefore, newPosts.size)
    }
}