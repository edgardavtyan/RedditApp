package com.ed.redditapp.ui.postlist;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private String title;
    private String username;
    private String subreddit;
    private String subredditIconUrl;
    private String permalink;
    private PostThumbnail thumbnailSmall;
    private PostThumbnail thumbnail320;
    private PostThumbnail thumbnail640;
    private PostThumbnail thumbnail960;
    private long timestamp;
    private int commentsCount;
    private int points;

    public Post() {
    }

    public Post(JSONObject json) throws JSONException {
        title = json.getString("title");
        username = json.getString("author");
        subreddit = json.getString("subreddit");
        permalink = json.getString("permalink");
        timestamp = json.getLong("created_utc");
        commentsCount = json.getInt("num_comments");
        points = json.getInt("ups");

        if (json.has("previews")) {
            JSONObject previews = json.getJSONObject("previews");
            if (previews.has("source"))
                thumbnailSmall = new PostThumbnail(previews.getJSONObject("source"));
            if (previews.has("preview320"))
                thumbnail320 = new PostThumbnail(previews.getJSONObject("preview320"));
            if (previews.has("preview640"))
                thumbnail640 = new PostThumbnail(previews.getJSONObject("preview640"));
            if (previews.has("preview960"))
                thumbnail960 = new PostThumbnail(previews.getJSONObject("preview960"));
        }
    }

    public PostThumbnail getLargestThumbnail() {
        if (thumbnail960 != null) return thumbnail960;
        if (thumbnail640 != null) return thumbnail640;
        if (thumbnail320 != null) return thumbnail320;
        if (thumbnailSmall != null) return thumbnailSmall;
        return null;
    }
}
