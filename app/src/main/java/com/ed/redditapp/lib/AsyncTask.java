package com.ed.redditapp.lib;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTask {
    public static class UIHandler {
        private final Handler handler;

        public UIHandler() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        public void onUIThread(Runnable r) {
            handler.post(r);
        }
    }

    public interface Task {
        void run(UIHandler h);
    }

    public void runAsync(Task task) {
        UIHandler handler = new UIHandler();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            task.run(handler);
        });
    }
}
