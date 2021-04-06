package com.ed.redditapp.ui.fragments.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;
import com.ed.redditapp.lib.api.SubReddit;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private final Context context;

    private SubReddit[] data;

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
        holder.setText(data[position].getName());
        holder.setInfo(data[position].getSubsCount());
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }

        return data.length;
    }

    public void updateData(SubReddit[] data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
