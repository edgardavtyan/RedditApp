package com.ed.redditapp.ui.imageview;

public class ImageViewModel {
    private final ImageDownloadAsyncTask imageDownloadAsyncTask;

    public ImageViewModel() {
        imageDownloadAsyncTask = new ImageDownloadAsyncTask();
    }

    public void downloadImage(String url, ImageDownloadAsyncTask.Callback callback) {
        imageDownloadAsyncTask.run(url, callback);
    }
}
