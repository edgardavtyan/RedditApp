package com.ed.redditapp.ui.fragments.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private final Context context;

    private String[] data;

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
        holder.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }

        return data.length;
    }

    public void updateData(String[] data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
