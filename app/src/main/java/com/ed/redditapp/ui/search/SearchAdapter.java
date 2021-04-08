package com.ed.redditapp.ui.search;

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
    private final SearchPresenter presenter;

    private SubReddit[] data;

    public SearchAdapter(Context context, SearchPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.listitem_simple, parent, false);
        return new SearchViewHolder(itemView, presenter);
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
