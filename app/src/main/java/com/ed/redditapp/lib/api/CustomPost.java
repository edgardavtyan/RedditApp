package com.ed.redditapp.lib.api;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CustomPost implements Post {
    private final String title;
    private final String username;
    private final String subreddit;
    private final String permalink;
    private final long timestamp;
    private final int commentsCount;
    private final int points;
    private PostThumbnail thumbnailSource;
    private PostThumbnail thumbnail320;
    private PostThumbnail thumbnail640;
    private PostThumbnail thumbnail960;

    @Setter
    private String subredditIconUrl;

    public CustomPost(JSONObject json) throws JSONException {
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
                thumbnailSource = new PostThumbnail(previews.getJSONObject("source"));
            if (previews.has("preview320"))
                thumbnail320 = new PostThumbnail(previews.getJSONObject("preview320"));
            if (previews.has("preview640"))
                thumbnail640 = new PostThumbnail(previews.getJSONObject("preview640"));
            if (previews.has("preview960"))
                thumbnail960 = new PostThumbnail(previews.getJSONObject("preview960"));
        }
    }
}
