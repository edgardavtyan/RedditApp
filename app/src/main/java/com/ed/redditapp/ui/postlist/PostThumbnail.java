package com.ed.redditapp.ui.postlist;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostThumbnail {
    private String url;
    private int width;
    private int height;

    public PostThumbnail() {
    }

    public PostThumbnail(JSONObject previewsJson) throws JSONException {
        url = previewsJson.getString("url");
        width = previewsJson.getInt("width");
        height = previewsJson.getInt("height");
    }
}
