package com.ed.redditapp.lib.api.kotlin

import com.ed.redditapp.lib.api.Comment
import org.json.JSONObject

class KotlinComment(json: JSONObject) : Comment {
    override val username: String = json.getString("author")
    override val body: String = json.getString("body_html")
    override val timestamp: Long = json.getLong("created_utc")
    override val points: Int = json.getInt("ups")
    override val indent: Int = json.getInt("indent")
}