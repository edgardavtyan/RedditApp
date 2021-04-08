package com.ed.redditapp.ui.activities.subreddit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ActivitySubredditBinding;
import com.ed.redditapp.lib.api.SubReddit;
import com.ed.redditapp.ui.postlist.PostListAdapter;
import com.ed.redditapp.ui.postlist.PostListItem;

import javax.inject.Inject;

public class SubRedditActivity extends AppCompatActivity {
    public static final String EXTRA_SUBREDDIT_NAME = "extra_subreddit_name";

    @Inject SubRedditPresenter presenter;
    @Inject PostListAdapter adapter;

    private ActivitySubredditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySubredditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerSubRedditActivityDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .subRedditDaggerModule(new SubRedditDaggerModule(this))
                .build()
                .inject(this);

        String subredditTitle = getIntent().getStringExtra(EXTRA_SUBREDDIT_NAME);
        presenter.onActivityCreated(subredditTitle);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
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
    }

    public void updatePosts(PostListItem[] posts) {
        adapter.updateData(posts);
    }
}
