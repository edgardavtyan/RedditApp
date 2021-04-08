package com.ed.redditapp.ui.fragments.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.databinding.FragmentSearchBinding;
import com.ed.redditapp.lib.TextChangedWatcher;
import com.ed.redditapp.lib.api.SubReddit;
import com.ed.redditapp.ui.activities.main.MainActivity;
import com.ed.redditapp.ui.activities.subreddit.SubredditActivity;

import javax.inject.Inject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SearchFragment extends Fragment {
    @Inject SearchPresenter presenter;
    @Inject SearchAdapter adapter;

    private FragmentSearchBinding binding;
    private MainActivity activity;
    private InputMethodManager inputMethodManager;

    private final View.OnClickListener onBtnCloseClickListener = v -> presenter.onBtnCloseClick(v);
    private final TextChangedWatcher searchTextWatcher = text -> presenter.onSearchTextChanged(text);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

        DaggerSearchFragmentDaggerComponent
                .builder()
                .appDaggerComponent(((App) activity.getApplication()).getAppComponent())
                .searchFragmentDaggerModule(new SearchFragmentDaggerModule(this))
                .build()
                .inject(this);

        binding = FragmentSearchBinding.inflate(inflater, container, false);

        binding.list.setLayoutManager(new LinearLayoutManager(activity));
        binding.list.setAdapter(adapter);

        binding.btnClose.setOnClickListener(onBtnCloseClickListener);
        binding.searchText.addTextChangedListener(searchTextWatcher);

        inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        binding.getRoot().setVisibility(View.GONE);

        return binding.getRoot();
    }

    public void updateSearchResults(SubReddit[] data) {
        adapter.updateData(data);
    }

    public void close() {
        binding.getRoot().setVisibility(View.GONE);
        inputMethodManager.hideSoftInputFromWindow(binding.searchText.getWindowToken(), 0);
    }

    public void show() {
        binding.getRoot().setVisibility(View.VISIBLE);
        binding.searchText.requestFocus();
        inputMethodManager.showSoftInput(binding.searchText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void gotoSubRedditActivity(SubReddit subreddit) {
        Intent intent = new Intent(activity, SubredditActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SubredditActivity.EXTRA_SUBREDDIT_NAME, subreddit.getName());
        activity.startActivity(intent);
    }
}
