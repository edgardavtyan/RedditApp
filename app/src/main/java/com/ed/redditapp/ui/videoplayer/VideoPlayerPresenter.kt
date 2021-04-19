package com.ed.redditapp.ui.videoplayer

class VideoPlayerPresenter(
        private val view: VideoPlayerActivity,
        private val model: VideoPlayerModel) {

    fun onLoaded(url: String) {
        view.showVideo(model.getVideoUrl(url));
    }
}