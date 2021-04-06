package com.ed.redditapp.ui.fragments.search;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ListitemSimpleBinding;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private final ListitemSimpleBinding binding;
    private final Context context;

    public SearchViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        binding = ListitemSimpleBinding.bind(itemView);
    }

    public void setText(String text) {
        binding.textPrimary.setText(text);
    }

    public void setInfo(int subsCount) {
        String infoFormatString = context.getResources().getString(R.string.search_listitem_subs_pattern);
        binding.textSecondary.setText(String.format(infoFormatString, subsCount));
    }
}
