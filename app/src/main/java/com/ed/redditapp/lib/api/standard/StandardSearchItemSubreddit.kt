package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.SearchItemSubreddit
import org.json.JSONObject

class StandardSearchItemSubreddit(json: JSONObject) : SearchItemSubreddit {
    override val name = json.getString("display_name")
    override val subsCount = json.getInt("subscribers")
}