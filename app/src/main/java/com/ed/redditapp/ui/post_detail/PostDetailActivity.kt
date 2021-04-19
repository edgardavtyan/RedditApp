package com.ed.redditapp.ui.post_detail

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ed.redditapp.App
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.databinding.ActivityPostDetailBinding
import com.ed.redditapp.lib.api.Comment
import javax.inject.Inject

class PostDetailActivity: BaseActivity<ActivityPostDetailBinding>() {
    companion object {
        const val EXTRA_POST_URL = "extra_post_url"
    }

    @Inject lateinit var adapter: PostDetailAdapter
    @Inject lateinit var presenter: PostDetailPresenter

    override val binding = ActivityPostDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerPostDetailDaggerComponent
                .builder()
                .appDaggerComponent((application as App).appComponent)
                .postDetailDaggerModule(PostDetailDaggerModule(this))
                .build()
                .inject(this)

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        presenter.onActivityLoaded(intent.getStringExtra(EXTRA_POST_URL)!!)
    }

    fun updateComments(comments: Array<Comment>) {
        adapter.updateComments(comments)
    }
}