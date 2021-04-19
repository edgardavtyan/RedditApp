package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.lib.api.SubReddit
import org.json.JSONObject

class StandardSubreddit(json: JSONObject) : SubReddit {
    override val name = json.getString("display_name")
    override val title = json.getString("title")
    override val description = json.getString("public_description")
    override val iconUrl: String
    override val subsCount = json.getInt("subscribers")

    init {
        val icon = json.getString("icon_img")
        val communityIcon = json.getString("community_icon")
        iconUrl = if (icon.isNotEmpty()) icon else communityIcon
    }
}