package com.ed.redditapp.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ed.redditapp.App
import com.ed.redditapp.Navigator
import com.ed.redditapp.databinding.FragmentSearchBinding
import com.ed.redditapp.lib.TextChangedWatcher
import com.ed.redditapp.lib.api.SearchItemSubreddit
import com.ed.redditapp.ui.main.MainActivity
import javax.inject.Inject

class SearchFragment : Fragment() {
    @Inject lateinit var presenter: SearchPresenter
    @Inject lateinit var adapter: SearchAdapter

    private lateinit var binding: FragmentSearchBinding
    private lateinit var activity: MainActivity
    private lateinit var inputManager: InputMethodManager

    private val onBtnCloseClickListener = presenter::onBtnCloseClick
    private val searchTextWatcher = TextChangedWatcher (presenter::onSearchTextChanged)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        activity = getActivity() as MainActivity

        DaggerSearchDaggerComponent
                .builder()
                .appDaggerComponent((activity.application as App).appComponent)
                .searchDaggerModule(SearchDaggerModule(this))
                .build()
                .inject(this)

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.adapter = adapter
        binding.btnClose.setOnClickListener(onBtnCloseClickListener)
        binding.searchText.addTextChangedListener(searchTextWatcher)
        inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.root.visibility = View.GONE
        return binding.root
    }

    fun updateSearchResults(data: Array<SearchItemSubreddit>) {
        adapter.updateData(data)
    }

    fun close() {
        binding.root.visibility = View.GONE
        inputManager.hideSoftInputFromWindow(binding.searchText.windowToken, 0)
    }

    fun show() {
        binding.root.visibility = View.VISIBLE
        binding.searchText.requestFocus()
        inputManager.showSoftInput(binding.searchText, InputMethodManager.SHOW_IMPLICIT)
    }

    fun gotoSubRedditActivity(subreddit: SearchItemSubreddit) {
        Navigator.gotoSubreddit(activity, subreddit.name)
    }
}