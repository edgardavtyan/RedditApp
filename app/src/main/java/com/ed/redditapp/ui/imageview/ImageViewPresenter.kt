package com.ed.redditapp.ui.imageview

class ImageViewPresenter(
        private val view: ImageViewActivity,
        private val model: ImageViewModel) {

    fun onLoaded(url: String) {
        view.setImage(url)
    }
}