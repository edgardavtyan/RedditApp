package com.ed.redditapp.ui.imageview;

public class ImageViewPresenter {
    private final ImageViewActivity view;
    private final ImageViewModel model;

    public ImageViewPresenter(ImageViewActivity view, ImageViewModel model) {
        this.view = view;
        this.model = model;
    }

    public void onLoaded(String url) {
        model.downloadImage(url, view::setImage);
    }
}
