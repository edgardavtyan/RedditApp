package com.ed.redditapp.lib.api.standard;

import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.PostThumbnail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
public class StandardPost implements Post {
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

    public StandardPost(JSONObject json) throws JSONException {
        title = json.getString("title");
        username = json.getString("author");
        subreddit = json.getString("subreddit");
        commentsCount = json.getInt("num_comments");
        points = json.getInt("ups");
        timestamp = json.getLong("created_utc");
        permalink = json.getString("permalink");

        if (json.has("preview")) {
            JSONArray thumbsJson = json
                    .getJSONObject("preview")
                    .getJSONArray("images")
                    .getJSONObject(0)
                    .getJSONArray("resolutions");

            if (thumbsJson.length() < 3) {
                JSONObject thumbSourceJson = json
                        .getJSONObject("preview")
                        .getJSONArray("images")
                        .getJSONObject(0)
                        .getJSONObject("source");
                thumbnailSource = new PostThumbnail(thumbSourceJson);
            }

            if (thumbsJson.length() >= 3) {
                thumbnail320 = new PostThumbnail(thumbsJson.getJSONObject(3));
            }

            if (thumbsJson.length() >= 4) {
                thumbnail640 = new PostThumbnail(thumbsJson.getJSONObject(4));
            }

            if (thumbsJson.length() >= 5) {
                thumbnail960 = new PostThumbnail(thumbsJson.getJSONObject(4));
            }
        }
    }
}
