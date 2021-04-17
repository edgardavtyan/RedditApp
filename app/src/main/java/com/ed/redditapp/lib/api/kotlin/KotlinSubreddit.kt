package com.ed.redditapp.lib.api.kotlin

import com.ed.redditapp.lib.api.SubReddit
import org.json.JSONObject

class KotlinSubreddit(json: JSONObject) : SubReddit {
    override val name: String = json.getString("name")
    override val title: String = json.getString("title")
    override val description: String = json.getString("public_description")
    override val iconUrl: String = json.getString("icon")
    override val subsCount: Int = json.getInt("subs")
}