package com.ed.redditapp.ui.imageview

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ed.redditapp.databinding.ActivityImageviewBinding
import javax.inject.Inject

class ImageViewActivity : AppCompatActivity() {
    companion object {
        val EXTRA_IMAGE_URL = "extra_image_url"
    }

    private lateinit var binding: ActivityImageviewBinding

    @Inject lateinit var presenter: ImageViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

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