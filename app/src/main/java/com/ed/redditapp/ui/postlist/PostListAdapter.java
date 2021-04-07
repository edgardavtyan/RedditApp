package com.ed.redditapp.ui.postlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;

public class PostListAdapter extends RecyclerView.Adapter<PostListViewHolder> {
    private Post[] posts;

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_post, parent, false);
        return new PostListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        Post post = posts[position];
        holder.setTitle(post.getTitle());
        holder.setCommentsCount(post.getCommentsCount());
        holder.setInfo(post.getPoints(), post.getTimestamp(), post.getUsername());
        holder.setThumbnail(post.getThumbnail960Url());
    }

    @Override
    public int getItemCount() {
        if (posts == null) {
            return 0;
        }

        return posts.length;
    }

    public void updateData(Post[] posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
