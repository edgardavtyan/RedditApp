package com.ed.redditapp.lib.api.standard;

import com.ed.redditapp.lib.api.SubReddit;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class StandardSubreddit implements SubReddit {
    private final String name;
    private final String title;
    private final String description;
    private final String iconUrl;
    private final int subsCount;

    public StandardSubreddit(JSONObject json) throws JSONException {
        name = json.getString("display_name");
        subsCount = json.getInt("subscribers");
        title = json.getString("title");
        description = json.getString("public_description");

        String icon = json.getString("icon_img");
        String communityIcon = json.getString("community_icon");
        iconUrl = !icon.isEmpty() ? icon : communityIcon;
    }
}
