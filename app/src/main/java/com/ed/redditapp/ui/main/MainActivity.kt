package com.ed.redditapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ed.redditapp.App
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.Navigator
import com.ed.redditapp.R
import com.ed.redditapp.databinding.ActivityMainBinding
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.ui.postlist.PostListAdapter
import com.ed.redditapp.ui.search.SearchFragment
import javax.inject.Inject

class MainActivity:
        BaseActivity<ActivityMainBinding>(),
        Toolbar.OnMenuItemClickListener {
    private lateinit var searchFragment: SearchFragment

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var adapter: PostListAdapter
    @Inject lateinit var presenter: MainPresenter

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainDaggerComponent
                .builder()
                .appDaggerComponent((application as App).appComponent)
                .mainDaggerModule(MainDaggerModule(this))
                .build()
                .inject(this)

        binding.toolbar.inflateMenu(R.menu.menu_main)
        binding.toolbar.setOnMenuItemClickListener(this)

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
        
        adapter.infoClickListener = presenter::onPostInfoClicked
        adapter.titleClickListener = presenter::onPostTitleClicked
        adapter.thumbnailClickListener = presenter::onThumbnailClicked

        searchFragment = SearchFragment()

        supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_search, searchFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                searchFragment.show()
                return true
            }
        }

        return false
    }

    fun updatePosts(posts: Array<Post>) {
        adapter.updateData(posts)
    }

    fun updateSubredditIcons(iconUrl: String, position: Int) {
        adapter.updateIcon(iconUrl, position)
    }

    fun gotoSubreddit(subreddit: String) {
        navigator.gotoSubreddit(this, subreddit)
    }

    fun gotoPostDetail(post: Post) {
        navigator.gotoPostDetail(this, post)
    }

    fun gotoImageView(post: Post) {
        navigator.gotoImageView(this, post.thumbnailSource?.url)
    }
}