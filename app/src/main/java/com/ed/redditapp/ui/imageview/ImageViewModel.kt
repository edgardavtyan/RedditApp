package com.ed.redditapp.ui.imageview

class ImageViewModel {
    private val imageDownloadAsyncTask = ImageDownloadAsyncTask()

    fun downloadImage(url: String, callback: ImageDownloadCallback) {
        imageDownloadAsyncTask.run(url, callback)
    }
}