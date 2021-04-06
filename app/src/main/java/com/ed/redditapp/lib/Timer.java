package com.ed.redditapp.lib;

import android.os.Handler;
import android.os.Looper;

public class Timer {
    private final int interval;
    private final Handler handler;
    private final Runnable runnable;

    public Timer(int interval, Runnable runnable) {
        this.interval = interval;
        this.runnable = runnable;
        handler = new Handler(Looper.getMainLooper());
    }

    public void run() {
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, interval);
    }
}
