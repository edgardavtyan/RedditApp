package com.ed.redditapp.lib.api.kotlin

import com.ed.redditapp.lib.api.SearchItemSubreddit
import org.json.JSONObject

class KotlinSearchItemSubreddit(json: JSONObject) : SearchItemSubreddit {
    override val name: String = json.getString("name")
    override val subsCount: Int = json.getInt("subs")
}