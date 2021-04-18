package com.ed.redditapp.ui.search

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.ed.redditapp.lib.api.SearchItemSubreddit
import android.view.ViewGroup
import android.view.LayoutInflater
import com.ed.redditapp.R

class SearchAdapter(
        private val context: Context,
        private val presenter: SearchPresenter)
    : RecyclerView.Adapter<SearchViewHolder>() {

    private var data: Array<SearchItemSubreddit> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.listitem_simple, parent, false)
        return SearchViewHolder(itemView, presenter)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setText(data[position].name)
        holder.setInfo(data[position].subsCount)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: Array<SearchItemSubreddit>) {
        this.data = data
        notifyDataSetChanged()
    }
}