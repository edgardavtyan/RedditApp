package com.ed.redditapp.ui.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ActivityMainBinding;
import com.ed.redditapp.ui.activities.subreddit.SubRedditActivity;
import com.ed.redditapp.ui.fragments.search.SearchFragment;
import com.ed.redditapp.ui.postlist.PostListAdapter;
import com.ed.redditapp.ui.postlist.PostListItem;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity
        extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {

    private static final String FRAGMENT_TAG_SEARCH = "fragment_search";

    private ActivityMainBinding binding;
    private SearchFragment searchFragment;

    @Inject PostListAdapter postListAdapter;
    @Inject MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerMainActivityDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .mainActivityDaggerModule(new MainActivityDaggerModule(this))
                .build()
                .inject(this);

        binding.toolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.setOnMenuItemClickListener(this);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(postListAdapter);

        postListAdapter.setInfoClickListener(presenter::onPostInfoClicked);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_search, SearchFragment.class, null, FRAGMENT_TAG_SEARCH)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_SEARCH);

        presenter.onActivityLoaded();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                searchFragment.show();
                return true;
        }
        return false;
    }

    public void updatePosts(PostListItem[] posts) {
        postListAdapter.updateData(posts);
    }

    public void updateSubredditIcons(String icon, int position) {
        postListAdapter.updateIcon(icon, position);
    }

    public void gotoSubreddit(String subredditName) {
        Intent intent = new Intent(this, SubRedditActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SubRedditActivity.EXTRA_SUBREDDIT_NAME, subredditName);
        startActivity(intent);
    }
}