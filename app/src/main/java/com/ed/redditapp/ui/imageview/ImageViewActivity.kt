package com.ed.redditapp.ui.imageview

import android.net.Uri
import android.os.Bundle
import com.ed.redditapp.BaseActivity
import com.ed.redditapp.databinding.ActivityImageviewBinding
import javax.inject.Inject

class ImageViewActivity: BaseActivity<ActivityImageviewBinding>() {
    companion object {
        val EXTRA_IMAGE_URL = "extra_image_url"
    }

    override val binding = ActivityImageviewBinding.inflate(layoutInflater)

    @Inject lateinit var presenter: ImageViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerImageViewDaggerComponent
                .builder()
                .imageViewDaggerModule(ImageViewDaggerModule(this))
                .build()
                .inject(this)

        presenter.onLoaded(intent.getStringExtra(EXTRA_IMAGE_URL)!!)
    }

    fun setImage(url: String) {
        binding.imageview.showImage(Uri.parse(url))
    }
}