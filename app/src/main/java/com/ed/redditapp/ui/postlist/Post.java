package com.ed.redditapp.ui.postlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private String title;
    private String username;
    private long timestamp;
    private int commentsCount;
    private int points;
}
