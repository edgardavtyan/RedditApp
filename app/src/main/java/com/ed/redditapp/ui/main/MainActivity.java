package com.ed.redditapp.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.Navigator;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ActivityMainBinding;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.ui.postlist.PostListAdapter;
import com.ed.redditapp.ui.search.SearchFragment;

import javax.inject.Inject;

public class MainActivity
        extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {

    private static final String FRAGMENT_TAG_SEARCH = "fragment_search";

    private ActivityMainBinding binding;
    private SearchFragment searchFragment;

    @Inject PostListAdapter postListAdapter;
    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerMainDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .mainDaggerModule(new MainDaggerModule(this))
                .build()
                .inject(this);

        binding.toolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.setOnMenuItemClickListener(this);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(postListAdapter);

        postListAdapter.setInfoClickListener(presenter::onPostInfoClicked);
        postListAdapter.setTitleClickListener(presenter::onPostTitleClicked);
        postListAdapter.setThumbnailClickListener(presenter::onThumbnailClicked);

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

    public void updatePosts(Post[] posts) {
        postListAdapter.updateData(posts);
    }

    public void updateSubredditIcons(String icon, int position) {
        postListAdapter.updateIcon(icon, position);
    }

    public void gotoSubreddit(String subredditName) {
        Navigator.gotoSubreddit(this, subredditName);
    }

    public void gotoPostDetail(Post post) {
        Navigator.gotoPostDetail(this, post);
    }

    public void gotoImageView(Post post) {
        Navigator.gotoImageView(this, post.getThumbnailSource().getUrl());
    }
}