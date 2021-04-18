package com.ed.redditapp.ui.imageview

import android.graphics.Bitmap
import com.ed.redditapp.lib.AsyncTask
import com.ed.redditapp.lib.http.HttpClient

typealias ImageDownloadCallback = (image: Bitmap) -> Unit

class ImageDownloadAsyncTask {
    private val httpClient = HttpClient()

    fun run(url: String, callback: ImageDownloadCallback) {
        AsyncTask().runAsync { h ->
            val bitmap = httpClient.getBitmap(url)
            h.onUIThread { callback(bitmap) }
        }
    }
}