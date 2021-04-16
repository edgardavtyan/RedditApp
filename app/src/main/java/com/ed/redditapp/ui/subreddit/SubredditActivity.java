package com.ed.redditapp.ui.subreddit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ed.redditapp.App;
import com.ed.redditapp.Navigator;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ActivitySubredditBinding;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.SubReddit;
import com.ed.redditapp.ui.postlist.PostListAdapter;

import javax.inject.Inject;

public class SubredditActivity extends AppCompatActivity {
    public static final String EXTRA_SUBREDDIT_NAME = "extra_subreddit_name";

    @Inject SubredditPresenter presenter;
    @Inject PostListAdapter adapter;

    private ActivitySubredditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubredditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerSubredditDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .subredditDaggerModule(new SubredditDaggerModule(this))
                .build()
                .inject(this);

        String subredditTitle = getIntent().getStringExtra(EXTRA_SUBREDDIT_NAME);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter.setDisplayIcon(false);
        adapter.setTitleClickListener(presenter::onPostTitleClicked);
        adapter.setThumbnailClickListener(presenter::onThumbnailClicked);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
        binding.list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int parallax = (int) (binding.list.computeVerticalScrollOffset() * 0.7);
                binding.header.setTranslationY(-parallax);
                binding.shadow.setTranslationY(-parallax + binding.header.getHeight());
            }
        });

        presenter.onActivityCreated(subredditTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateSubredditInfo(SubReddit subreddit) {
        binding.toolbar.setTitle(subreddit.getName().trim());
        binding.title.setText(subreddit.getTitle().trim());
        binding.subsCount.setText(String.format(getString(R.string.search_listitem_subs_pattern), subreddit.getSubsCount()));
        binding.description.setText(subreddit.getDescription().replaceAll("\\n", ""));
        Glide.with(this)
                .load(subreddit.getIconUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(binding.icon);

        binding.header.post(() -> {
            binding.list.addItemDecoration(new FirstItemOffsetDecoration(binding.header.getHeight()));
            binding.shadow.setY(binding.header.getHeight());
        });
    }

    public void updatePosts(Post[] posts) {
        adapter.updateData(posts);
    }

    public void gotoPostDetail(Post post) {
        Navigator.gotoPostDetail(this, post);
    }

    public void gotoImageView(String url) {
        Navigator.gotoImageView(this, url);
    }
}
