package com.ed.redditapp.ui.post_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;
import com.ed.redditapp.lib.api.Comment;

public class PostDetailAdapter extends RecyclerView.Adapter<PostDetailViewHolder> {
    private Comment[] comments;

    @NonNull
    @Override
    public PostDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_comment, parent, false);
        return new PostDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostDetailViewHolder holder, int position) {
        holder.setUsername(comments[position].getUsername());
        holder.setBody(comments[position].getBody());
        holder.setIndent(comments[position].getIndent());
    }

    @Override
    public int getItemCount() {
        if (comments == null) {
            return 0;
        }

        return comments.length;
    }

    public void updateComments(Comment[] comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }
}
