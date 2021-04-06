package com.ed.redditapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ed.redditapp.databinding.FragmentSearchBinding;

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

        binding.btnClose.setOnClickListener(onBtnCloseClickListener);

        return binding.getRoot();
    }
}
