package com.ed.redditapp.lib.api;

import android.text.Html;

import androidx.annotation.Nullable;

import com.ed.redditapp.lib.http.HttpClient;
import com.ed.redditapp.ui.postlist.Post;
import com.ed.redditapp.ui.postlist.PostThumbnail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RedditApi {
    private static final String URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on";
    private static final String URL_SUBREDDIT_ABOUT = "https://www.reddit.com/r/%s/about.json";
    private static final String USL_SUBREDDIT_ROOT = "https://www.reddit.com/r/%s.json";
    private static final String URL_COMMENTS = "https://www.reddit.com%s.json";
    private final HttpClient httpClient;

    public RedditApi() {
        httpClient = new HttpClient();
    }

    @Nullable
    public SubReddit[] searchSubreddits(String query) {
        try {
            String url = String.format(URL_SEARCH_SUBREDDIT, query);
            JSONArray entries = httpClient.getJson(url).getJSONObject("data").getJSONArray("children");
            SubReddit[] results = new SubReddit[entries.length()];
            for (int i = 0; i < entries.length(); i++) {
                SubReddit subReddit = new SubReddit();
                JSONObject data = entries.getJSONObject(i).getJSONObject("data");
                subReddit.setName(data.getString("display_name"));
                subReddit.setSubsCount(data.getInt("subscribers"));
                results[i] = subReddit;
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }

    public SubReddit getSubredditInfo(String subredditName) {
        try {
            String url = String.format(URL_SUBREDDIT_ABOUT, subredditName);
            JSONObject json = httpClient.getJson(url).getJSONObject("data");

            SubReddit subreddit = new SubReddit();
            subreddit.setName(json.getString("display_name"));
            subreddit.setSubsCount(json.getInt("subscribers"));
            subreddit.setTitle(json.getString("title"));
            subreddit.setDescription(json.getString("public_description"));
            subreddit.setIconUrl(getSubredditIconUrl(subredditName));

            return subreddit;
        } catch (Exception e) {
            return null;
        }
    }

    public Post[] getSubredditPosts(String subredditName) {
        try {
            JSONArray postsJson = httpClient
                    .getJson(String.format(USL_SUBREDDIT_ROOT, subredditName))
                    .getJSONObject("data")
                    .getJSONArray("children");

            Post[] posts = new Post[postsJson.length()];
            for (int i = 0; i < postsJson.length(); i++) {
                JSONObject postJson = postsJson.getJSONObject(i).getJSONObject("data");
                Post post = new Post();
                post.setTitle(postJson.getString("title"));
                post.setUsername(postJson.getString("author"));
                post.setSubreddit(postJson.getString("subreddit"));
                post.setCommentsCount(postJson.getInt("num_comments"));
                post.setPoints(postJson.getInt("ups"));
                post.setTimestamp(postJson.getLong("created_utc"));
                post.setPermalink(postJson.getString("permalink"));

                if (postJson.has("preview")) {
                    JSONArray thumbsJson = postJson
                            .getJSONObject("preview")
                            .getJSONArray("images")
                            .getJSONObject(0)
                            .getJSONArray("resolutions");

                    if (thumbsJson.length() < 3) {
                        PostThumbnail thumbSmall = new PostThumbnail();
                        JSONObject thumbSmallJson = postJson
                                .getJSONObject("preview")
                                .getJSONArray("images")
                                .getJSONObject(0)
                                .getJSONObject("source");
                        thumbSmall.setWidth(thumbSmallJson.getInt("width"));
                        thumbSmall.setHeight(thumbSmallJson.getInt("height"));
                        thumbSmall.setUrl(thumbSmallJson.getString("url"));
                        post.setThumbnailSmall(thumbSmall);
                    }

                    if (thumbsJson.length() >= 4) {
                        post.setThumbnail320(getPostThumbnail(thumbsJson, 2));
                    }

                    if (thumbsJson.length() >= 5) {
                        post.setThumbnail640(getPostThumbnail(thumbsJson, 3));
                    }

                    if (thumbsJson.length() >= 6) {
                        post.setThumbnail960(getPostThumbnail(thumbsJson, 4));
                    }
                }

                posts[i] = post;
            }

            return posts;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSubredditIconUrl(String subreddit) {
        try {
            String url = String.format(URL_SUBREDDIT_ABOUT, subreddit);
            JSONObject json = httpClient.getJson(url).getJSONObject("data");

            String iconUrl = Html.fromHtml(json.getString("icon_img")).toString();
            String communityIcon = Html.fromHtml(json.getString("community_icon")).toString();
            return !iconUrl.isEmpty() ? iconUrl : communityIcon;
        } catch (Exception e) {
            return null;
        }
    }

    public Comment[] getPostComments(String postUrl) {
        try {
            String url = String.format(URL_COMMENTS, postUrl);
            JSONArray commentsJson = httpClient
                    .getArray(url)
                    .getJSONObject(1)
                    .getJSONObject("data")
                    .getJSONArray("children");

            Comment[] comments = new Comment[commentsJson.length() - 1];
            for (int i = 0; i < commentsJson.length() - 1; i++) {
                JSONObject commentJson = commentsJson.getJSONObject(i).getJSONObject("data");
                Comment comment = new Comment();
                comment.setUsername(commentJson.getString("author"));
                comments[i] = comment;
            }

            return comments;
        } catch (Exception e) {
            return null;
        }
    }

    private PostThumbnail getPostThumbnail(JSONArray thumbsArray, int index) throws JSONException {
        PostThumbnail thumb = new PostThumbnail();
        thumb.setWidth(thumbsArray.getJSONObject(index).getInt("width"));
        thumb.setHeight(thumbsArray.getJSONObject(index).getInt("height"));
        thumb.setUrl(thumbsArray.getJSONObject(index).getString("url"));
        return thumb;
    }
}
