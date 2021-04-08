package com.ed.redditapp.lib.api;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubReddit {
    private String name;
    private String title;
    private String description;
    private String iconUrl;
    private int subsCount;
}
