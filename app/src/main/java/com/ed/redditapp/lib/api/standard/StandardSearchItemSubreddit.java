package com.ed.redditapp.lib.api.standard;

import com.ed.redditapp.lib.api.SearchItemSubreddit;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class StandardSearchItemSubreddit implements SearchItemSubreddit {
    private final String name;
    private final int subsCount;

    public StandardSearchItemSubreddit(JSONObject json) throws JSONException {
        name = json.getString("display_name");
        subsCount = json.getInt("subscribers");
    }
}
