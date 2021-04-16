package com.ed.redditapp.lib.api.custom;

import com.ed.redditapp.lib.api.SubReddit;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class CustomSubreddit implements SubReddit {
    private final String name;
    private final String title;
    private final String description;
    private final String iconUrl;
    private final int subsCount;

    public CustomSubreddit(JSONObject aboutJson) throws JSONException {
        name = aboutJson.getString("name");
        title = aboutJson.getString("title");
        description = aboutJson.getString("public_description");
        iconUrl = aboutJson.getString("icon");
        subsCount = aboutJson.getInt("subs");
    }
}
