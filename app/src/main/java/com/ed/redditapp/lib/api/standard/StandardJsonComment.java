package com.ed.redditapp.lib.api.standard;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
public class StandardJsonComment {
    private final JSONObject data;
    private int indent;

    public StandardJsonComment(JSONObject data, int indent) {
        this.data = data;
        this.indent = indent;
    }

    public void incrementIndent() {
        indent++;
    }
}
