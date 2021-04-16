package com.ed.redditapp.lib.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private ArrayList<Comment> replies;
    private String username;
    private String body;
    private long timestamp;
    private int points;
    private int indent;

    public Comment() {
    }

    public Comment(JSONObject json) throws JSONException {
        username = json.getString("author");
        body = json.getString("body");
        timestamp = json.getLong("crated_utc");
        points = json.getInt("ups");
        indent = json.getInt("indent");
    }
}
