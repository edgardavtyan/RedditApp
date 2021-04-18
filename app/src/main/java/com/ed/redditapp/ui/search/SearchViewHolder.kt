package com.ed.redditapp.ui.search

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ed.redditapp.R
import com.ed.redditapp.databinding.ListitemSimpleBinding
import com.ed.redditapp.format

class SearchViewHolder(itemView: View, presenter: SearchPresenter)
    : RecyclerView.ViewHolder(itemView) {

    private val binding = ListitemSimpleBinding.bind(itemView)
    private val context = itemView.context

    init {
        itemView.setOnClickListener { presenter.onItemClick(adapterPosition) }
    }

    fun setText(text: String?) {
        binding.textPrimary.text = text
    }

    fun setInfo(subsCount: Int) {
        val infoFormatString = context.getString(R.string.search_listitem_subs_pattern)
        binding.textSecondary.text = infoFormatString.format(subsCount)
    }
}