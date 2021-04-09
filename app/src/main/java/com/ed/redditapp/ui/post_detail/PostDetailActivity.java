package com.ed.redditapp.ui.post_detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.databinding.ActivityPostDetailBinding;
import com.ed.redditapp.lib.api.Comment;

import javax.inject.Inject;

public class PostDetailActivity extends AppCompatActivity {
    public static final String EXTRA_POST_URL = "extra_post_url";

    private ActivityPostDetailBinding binding;

    @Inject PostDetailAdapter adapter;
    @Inject PostDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPostDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerPostDetailDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .postDetailDaggerModule(new PostDetailDaggerModule(this))
                .build()
                .inject(this);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);

        presenter.onActivityLoaded(getIntent().getStringExtra(EXTRA_POST_URL));
    }

    public void updateComments(Comment[] comments) {
        adapter.updateComments(comments);
    }
}
