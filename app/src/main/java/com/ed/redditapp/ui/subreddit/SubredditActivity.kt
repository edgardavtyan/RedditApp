package com.ed.redditapp.ui.subreddit

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ed.redditapp.App
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.Navigator
import com.ed.redditapp.R
import com.ed.redditapp.databinding.ActivitySubredditBinding
import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.SubReddit
import com.ed.redditapp.ui.postlist.PostListAdapter
import javax.inject.Inject

class SubredditActivity: BaseActivity<ActivitySubredditBinding>() {
    companion object {
        val EXTRA_SUBREDDIT_NAME = "extra_subreddit_name"
    }

    override fun getViewBinding() = ActivitySubredditBinding.inflate(layoutInflater)

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var presenter: SubredditPresenter
    @Inject lateinit var adapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSubredditDaggerComponent
                .builder()
                .appDaggerComponent((application as App).appComponent)
                .subredditDaggerModule(SubredditDaggerModule(this))
                .build()
                .inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        adapter.isDisplayIcon = false
        adapter.titleClickListener = presenter::onPostTitleClicked
        adapter.thumbnailClickListener = presenter::onThumbnailClicked

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val parallax = (binding.list.computeVerticalScrollOffset() * 0.7).toInt()
                binding.header.translationY = -parallax.toFloat()
                binding.shadow.translationY = (-parallax + binding.header.height).toFloat()
            }
        })

        presenter.onActivityCreated(intent.getStringExtra(EXTRA_SUBREDDIT_NAME)!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateSubredditInfo(subreddit: SubReddit) {
        binding.toolbar.title = subreddit.name.trim()
        binding.title.text = subreddit.title.trim()
        binding.subsCount.text = getString(R.string.search_listitem_subs_pattern).format(subreddit.subsCount)
        binding.description.text = subreddit.description.replace("\\n", "")
        Glide.with(this)
                .load(subreddit.iconUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.icon)
        binding.header.post {
            binding.list.addItemDecoration(FirstItemOffsetDecoration(binding.header.height))
            binding.shadow.y = binding.header.height.toFloat()
        }
    }

    fun updatePosts(posts: Array<Post>) {
        adapter.updateData(posts)
    }

    fun gotoPostDetail(post: Post) {
        navigator.gotoPostDetail(this, post)
    }

    fun gotoImageView(url: String?) {
        navigator.gotoImageView(this, url)
    }

    fun gotoVideoView(url: String?) {
        navigator.gotoVideoView(this, url)
    }
}