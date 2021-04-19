package com.ed.redditapp.lib

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

typealias AsyncTaskRunnable = (h: AsyncTask.UIHandler) -> Unit

class AsyncTask {
    class UIHandler {
        private val handler = Handler(Looper.getMainLooper())
        fun onUIThread(r: Runnable) = handler.post(r)
    }

    fun runAsync(task: AsyncTaskRunnable) {
        val handler = UIHandler()
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { task(handler) }
    }
}