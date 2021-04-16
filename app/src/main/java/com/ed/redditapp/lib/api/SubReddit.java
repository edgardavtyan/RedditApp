package com.ed.redditapp.lib.api;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubReddit {
    private String name;
    private String title;
    private String description;
    private String iconUrl;
    private int subsCount;

    public SubReddit() {
    }

    public SubReddit(JSONObject aboutJson) throws JSONException {
        name = aboutJson.getString("name");
        title = aboutJson.getString("title");
        description = aboutJson.getString("public_description");
        iconUrl = aboutJson.getString("icon");
        subsCount = aboutJson.getInt("subs");
    }
}
