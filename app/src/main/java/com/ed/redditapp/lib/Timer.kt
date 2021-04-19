package com.ed.redditapp.lib

import android.os.Handler
import android.os.Looper

class Timer(private val interval: Int, private val runnable: Runnable) {
    private val handler = Handler(Looper.getMainLooper())

    fun run() {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, interval.toLong())
    }
}