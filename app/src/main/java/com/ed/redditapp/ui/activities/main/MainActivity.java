package com.ed.redditapp.ui.activities.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ed.redditapp.App;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ActivityMainBinding;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.fragments.search.SearchFragment;

import javax.inject.Inject;

public class MainActivity
        extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {

    private static final String FRAGMENT_TAG_SEARCH = "fragment_search";

    private ActivityMainBinding binding;
    private SearchFragment searchFragment;

    @Inject RedditApi redditApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DaggerMainActivityDaggerComponent
                .builder()
                .appDaggerComponent(((App) getApplication()).getAppComponent())
                .build()
                .inject(this);

        binding.toolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.setOnMenuItemClickListener(this);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_search, SearchFragment.class, null, FRAGMENT_TAG_SEARCH)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_SEARCH);
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
}