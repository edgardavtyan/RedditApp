package com.ed.redditapp.ui.imageview;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ed.redditapp.databinding.ActivityImageviewBinding;

import javax.inject.Inject;

public class ImageViewActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_URL = "extra_image_url";

    private ActivityImageviewBinding binding;

    @Inject ImageViewPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityImageviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerImageViewDaggerComponent
                .builder()
                .imageViewDaggerModule(new ImageViewDaggerModule(this))
                .build()
                .inject(this);

        presenter.onLoaded(getIntent().getStringExtra(EXTRA_IMAGE_URL));
    }

    public void setImage(Bitmap image) {
        binding.imageview.setImageBitmap(image);
    }
}
