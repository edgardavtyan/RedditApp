package com.ed.redditapp.ui.activities.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ed.redditapp.R;
import com.ed.redditapp.ui.fragments.search.SearchFragment;
import com.ed.redditapp.databinding.ActivityMainBinding;
import com.ed.redditapp.lib.api.RedditApi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity
        extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.inflateMenu(R.menu.menu_main);
        binding.toolbar.setOnMenuItemClickListener(this);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_search, SearchFragment.class, null)
                .commit();

        RedditApi api = new RedditApi();

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            String[] result = api.searchSubreddits("prizes");
            handler.post(() -> binding.text.setText(result.toString()));
        });
    }



    public void onSearchFragmentBtnCloseClick() {
        binding.fragmentSearch.setVisibility(View.GONE);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                binding.fragmentSearch.setVisibility(View.VISIBLE);
                return true;
        }
        return false;
    }
}