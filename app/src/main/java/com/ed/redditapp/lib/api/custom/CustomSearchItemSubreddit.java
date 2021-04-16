package com.ed.redditapp.lib.api.custom;

import com.ed.redditapp.lib.api.SearchItemSubreddit;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class CustomSearchItemSubreddit implements SearchItemSubreddit {
    private final String name;
    private final int subsCount;

    public CustomSearchItemSubreddit(JSONObject json) throws JSONException {
        name = json.getString("name");
        subsCount = json.getInt("subs");
    }
}
