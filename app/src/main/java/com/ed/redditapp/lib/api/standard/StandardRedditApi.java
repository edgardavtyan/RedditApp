package com.ed.redditapp.lib.api.standard;

import com.ed.redditapp.lib.api.Comment;
import com.ed.redditapp.lib.api.Post;
import com.ed.redditapp.lib.api.RedditApi;
import com.ed.redditapp.lib.api.SearchItemSubreddit;
import com.ed.redditapp.lib.api.SubReddit;
import com.ed.redditapp.lib.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Stack;

public class StandardRedditApi implements RedditApi {
    private static final String URL_SEARCH_SUBREDDIT = "https://www.reddit.com/subreddits/search.json?q=%s&include_over_18=on";
    private static final String URL_SUBREDDIT_ABOUT = "https://www.reddit.com/r/%s/about.json?raw_json=1";
    private static final String USL_SUBREDDIT_ROOT = "https://www.reddit.com/r/%s.json";
    private static final String URL_COMMENTS = "https://www.reddit.com%s.json?raw_json=1";
    private final HttpClient httpClient;

    public StandardRedditApi() {
        httpClient = new HttpClient();
    }

    @Override
    public SearchItemSubreddit[] searchSubreddits(String query) {
        try {
            String url = String.format(URL_SEARCH_SUBREDDIT, query);
            JSONArray entries = httpClient.getJson(url).getJSONObject("data").getJSONArray("children");
            SearchItemSubreddit[] results = new SearchItemSubreddit[entries.length()];
            for (int i = 0; i < entries.length(); i++) {
                JSONObject data = entries.getJSONObject(i).getJSONObject("data");
                results[i] = new StandardSearchItemSubreddit(data);
            }
            return results;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SubReddit getSubredditInfo(String subredditName) {
        try {
            String url = String.format(URL_SUBREDDIT_ABOUT, subredditName);
            return new StandardSubreddit(httpClient.getJson(url).getJSONObject("data"));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Post[] getSubredditPosts(String subredditName) {
        try {
            JSONArray postsJson = httpClient
                    .getJson(String.format(USL_SUBREDDIT_ROOT, subredditName))
                    .getJSONObject("data")
                    .getJSONArray("children");
            Post[] posts = new Post[postsJson.length()];
            for (int i = 0; i < postsJson.length(); i++) {
                posts[i] = new StandardPost(postsJson.getJSONObject(i).getJSONObject("data"));
            }

            return posts;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getSubredditIconUrl(String subreddit) {
        try {
            String url = String.format(URL_SUBREDDIT_ABOUT, subreddit);
            JSONObject json = httpClient.getJson(url).getJSONObject("data");

            String iconUrl = json.getString("icon_img");
            String communityIcon = json.getString("community_icon");
            return !iconUrl.isEmpty() ? iconUrl : communityIcon;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Comment[] getPostComments(String postUrl) {
        try {
            String url = String.format(URL_COMMENTS, postUrl);
            JSONArray commentsJson = httpClient
                    .getArray(url)
                    .getJSONObject(1)
                    .getJSONObject("data")
                    .getJSONArray("children");
            Stack<StandardJsonComment> jsonStack = new Stack<>();
            ArrayList<Comment> comments = new ArrayList<>();
            for (int i = commentsJson.length() - 1; i >= 0; i--) {
                if (commentsJson.getJSONObject(i).getString("kind").equals("t1")) {
                    JSONObject jsonComment = commentsJson.getJSONObject(i).getJSONObject("data");
                    jsonStack.push(new StandardJsonComment(jsonComment, 0));
                }
            }

            while (jsonStack.size() > 0) {
                StandardJsonComment jsonComment = jsonStack.pop();
                JSONObject data = jsonComment.getData();
                comments.add(new StandardComment(jsonComment));
                if (data.has("replies") && !data.get("replies").equals("")) {
                    jsonComment.incrementIndent();
                    JSONArray repliesJson = data
                            .getJSONObject("replies")
                            .getJSONObject("data")
                            .getJSONArray("children");
                    for (int i = repliesJson.length() - 1; i >= 0; i--) {
                        JSONObject replyJson = repliesJson.getJSONObject(i);
                        if (replyJson.getString("kind").equals("t1")) {
                            jsonStack.push(new StandardJsonComment(
                                    replyJson.getJSONObject("data"), jsonComment.getIndent()));
                        }
                    }
                }
            }

            Comment[] commentsArray = new Comment[comments.size()];
            comments.toArray(commentsArray);
            return commentsArray;
        } catch (Exception e) {
            return null;
        }
    }
}
