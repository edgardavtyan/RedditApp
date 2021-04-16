package com.ed.redditapp.lib.api;

public interface Comment {
    String getUsername();
    String getBody();
    long getTimestamp();
    int getPoints();
    int getIndent();
}
