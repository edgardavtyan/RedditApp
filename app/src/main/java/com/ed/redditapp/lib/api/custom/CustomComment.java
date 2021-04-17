package com.ed.redditapp.lib.api.custom;

import com.ed.redditapp.lib.api.Comment;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class CustomComment implements Comment {
    private final String username;
    private final String body;
    private final long timestamp;
    private final int points;
    private final int indent;

    public CustomComment(JSONObject json) throws JSONException {
        username = json.getString("author");
        body = json.getString("body_html");
        timestamp = json.getLong("created_utc");
        points = json.getInt("ups");
        indent = json.getInt("indent");
    }
}
