package com.ed.redditapp.lib.api

import org.json.JSONObject

class PostThumbnail(json: JSONObject) {
    val url: String = json.getString("url")
    val width: Int = json.getInt("width")
    val height: Int = json.getInt("height")
}