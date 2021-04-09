package com.ed.redditapp.ui.post_detail;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.databinding.ListitemCommentBinding;

public class PostDetailViewHolder extends RecyclerView.ViewHolder {
    private final ListitemCommentBinding binding;

    public PostDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ListitemCommentBinding.bind(itemView);
    }

    public void setUsername(String username) {
        binding.text.setText(username);
    }
}
