package com.ed.redditapp.ui.post_detail;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.databinding.ListitemCommentBinding;

public class PostDetailViewHolder extends RecyclerView.ViewHolder {
    private final ListitemCommentBinding binding;
    private final Context context;

    public PostDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ListitemCommentBinding.bind(itemView);
        context = itemView.getContext();
    }

    public void setUsername(String username) {
        binding.user.setText(username);
    }

    public void setBody(String body) {
        binding.body.setHtml(body);
    }

    public void setIndent(int indent) {
        int px = indent * 20 * context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        binding.getRoot().setTranslationX(px);
    }
}
