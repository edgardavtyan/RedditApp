package com.ed.redditapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.databinding.ListitemSimpleBinding;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private final ListitemSimpleBinding binding;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ListitemSimpleBinding.bind(itemView);
    }

    public void setText(String text) {
        binding.getRoot().setText(text);
    }
}
