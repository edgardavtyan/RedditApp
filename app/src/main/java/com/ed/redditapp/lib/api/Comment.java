package com.ed.redditapp.lib.api;

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
}
