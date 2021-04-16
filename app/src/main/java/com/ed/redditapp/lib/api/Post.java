package com.ed.redditapp.lib.api;

public interface Post {
    String getTitle();
    String getUsername();
    String getSubreddit();
    String getSubredditIconUrl();
    void setSubredditIconUrl(String url);
    String getPermalink();
    PostThumbnail getThumbnailSource();
    PostThumbnail getThumbnail320();
    PostThumbnail getThumbnail640();
    PostThumbnail getThumbnail960();
    long getTimestamp();
    int getCommentsCount();
    int getPoints();

    default PostThumbnail getLargestThumbnail() {
        if (getThumbnail960() != null) return getThumbnail960();
        if (getThumbnail640() != null) return getThumbnail640();
        if (getThumbnail320() != null) return getThumbnail320();
        if (getThumbnailSource() != null) return getThumbnailSource();
        return null;
    }
}
