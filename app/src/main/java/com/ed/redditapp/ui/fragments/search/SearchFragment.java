package com.ed.redditapp.ui.fragments.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.App;
import com.ed.redditapp.databinding.FragmentSearchBinding;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.ui.activities.main.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class SearchFragment extends Fragment {
    @Inject RedditApi redditApi;

    private FragmentSearchBinding binding;
    private MainActivity activity;

    private SearchAdapter adapter;

    private final View.OnClickListener onBtnCloseClickListener = (v) -> {
        activity.onSearchFragmentBtnCloseClick();
    };

    private final TextWatcher searchTextWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            Handler handler = new Handler(Looper.getMainLooper());
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(() -> {
                String[] result = redditApi.searchSubreddits(s.toString());
                handler.post(() -> adapter.updateData(result));
            });
        }

        @Override public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

        DaggerSearchFragmentDaggerComponent
                .builder()
                .appDaggerComponent(((App) activity.getApplication()).getAppComponent())
                .build()
                .inject(this);

        binding = FragmentSearchBinding.inflate(inflater, container, false);

        adapter = new SearchAdapter(activity);
        binding.list.setLayoutManager(new LinearLayoutManager(activity));
        binding.list.setAdapter(adapter);

        binding.btnClose.setOnClickListener(onBtnCloseClickListener);
        binding.searchText.addTextChangedListener(searchTextWatcher);

        return binding.getRoot();
    }
}
