package com.ed.redditapp.lib.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Comment[] replies;
    private String username;
}
