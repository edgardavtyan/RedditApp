package com.ed.redditapp.lib.api.standard;

import com.ed.redditapp.lib.api.Comment;

import org.json.JSONException;

import lombok.Getter;

@Getter
public class StandardComment implements Comment {
    private final String username;
    private final String body;
    private final long timestamp;
    private final int points;
    private final int indent;

    public StandardComment(StandardJsonComment jsonComment) throws JSONException {
        username = jsonComment.getData().getString("author");
        body = jsonComment.getData().getString("body_html");
        timestamp = jsonComment.getData().getLong("created_utc");
        points = jsonComment.getData().getInt("ups");
        indent = jsonComment.getIndent();
    }
}
