package com.ed.redditapp.ui.fragments.search;

import android.os.Bundle;
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
import com.ed.redditapp.ui.activities.main.MainActivity;

import javax.inject.Inject;

public class SearchFragment extends Fragment {
    @Inject SearchPresenter presenter;
    @Inject SearchAdapter adapter;

    private FragmentSearchBinding binding;
    private MainActivity activity;

    private final View.OnClickListener onBtnCloseClickListener = v -> presenter.onBtnCloseClick(v);

    private final TextWatcher searchTextWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.onSearchTextChanged(s.toString());
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
                .searchFragmentDaggerModule(new SearchFragmentDaggerModule(this))
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

    public void updateSearchResults(String[] data) {
        adapter.updateData(data);
    }

    public void close() {
        activity.closeSearchFragment();
    }
}
