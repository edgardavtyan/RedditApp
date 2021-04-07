package com.ed.redditapp.ui.postlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;

public class PostListAdapter extends RecyclerView.Adapter<PostListViewHolder> {
    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_DEFAULT = 0;

    private PostListItem[] posts;
    private int itemViewWidth;

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_post, parent, false);

        if (viewType == VIEW_TYPE_FIRST) {
            itemView.post(() -> itemViewWidth = itemView.getWidth());
        }

        return new PostListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        PostListItem post = posts[position];
        holder.setTitle(post.getTitle());
        holder.setCommentsCount(post.getCommentsCount());
        holder.setInfo(post.getPoints(), post.getTimestamp(), post.getUsername());
        holder.setThumbnail(post.getLargestThumbnail());
    }

    @Override
    public int getItemCount() {
        if (posts == null) {
            return 0;
        }

        return posts.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_FIRST;
        else
            return VIEW_TYPE_DEFAULT;
    }

    public void updateData(PostListItem[] posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
