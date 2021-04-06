package com.ed.redditapp.lib;

import android.text.Editable;
import android.text.TextWatcher;

public interface TextChangedWatcher extends TextWatcher {
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    default void afterTextChanged(Editable s) {}
    default void onTextChanged(CharSequence s, int start, int before, int count) {
        textChanged(s.toString());
    }

    void textChanged(String text);
}
