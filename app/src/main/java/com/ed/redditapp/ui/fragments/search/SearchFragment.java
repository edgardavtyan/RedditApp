package com.ed.redditapp.ui.fragments.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ed.redditapp.databinding.FragmentSearchBinding;
import com.ed.redditapp.ui.activities.main.MainActivity;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private MainActivity activity;

    private View.OnClickListener onBtnCloseClickListener = (v) -> {
        activity.onSearchFragmentBtnCloseClick();
    };

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

        binding = FragmentSearchBinding.inflate(inflater, container, false);

        binding.list.setLayoutManager(new LinearLayoutManager(activity));
        binding.list.setAdapter(new SearchAdapter(activity));

        binding.btnClose.setOnClickListener(onBtnCloseClickListener);

        return binding.getRoot();
    }
}
