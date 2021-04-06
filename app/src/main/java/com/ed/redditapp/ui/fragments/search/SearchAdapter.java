package com.ed.redditapp.ui.fragments.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;

import java.util.UUID;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private final Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.listitem_simple, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.setText(UUID.randomUUID().toString());
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
