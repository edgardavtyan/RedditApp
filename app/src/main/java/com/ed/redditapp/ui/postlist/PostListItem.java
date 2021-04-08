package com.ed.redditapp.ui.postlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListItem {
    private String title;
    private String username;
    private String subreddit;
    private String subredditIconUrl;
    private PostThumbnail thumbnailSmall;
    private PostThumbnail thumbnail320;
    private PostThumbnail thumbnail640;
    private PostThumbnail thumbnail960;
    private long timestamp;
    private int commentsCount;
    private int points;

    public PostThumbnail getLargestThumbnail() {
        if (thumbnail960 != null) return thumbnail960;
        if (thumbnail640 != null) return thumbnail640;
        if (thumbnail320 != null) return thumbnail320;
        if (thumbnailSmall != null) return thumbnailSmall;
        return null;
    }
}
