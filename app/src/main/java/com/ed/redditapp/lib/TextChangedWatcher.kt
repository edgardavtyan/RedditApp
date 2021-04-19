package com.ed.redditapp.lib

import android.text.Editable
import android.text.TextWatcher

interface TextChangedWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        textChanged(s.toString())
    }

    fun textChanged(text: String)
}