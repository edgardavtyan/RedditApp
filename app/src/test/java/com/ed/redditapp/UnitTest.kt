package com.ed.redditapp

import com.ed.redditapp.lib.api.PostContentType
import com.ed.redditapp.lib.api.standard.StandardPost
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UnitTest {
    private val res = ResourceReader()

    private lateinit var json: JSONObject
    private lateinit var post: StandardPost

    @Before
    fun beforeEach() {
        json = JSONObject(res.get("redditapi-post/previews-6.json"))
        post = StandardPost(json, "after")
    }

    @Test
    fun testAfter() {
        assertThat(post.after, equalTo("after"))
    }

    @Test
    fun testTitle() {
        assertThat(post.title, equalTo("PostTitle"))
    }

    @Test
    fun testUsername() {
        assertThat(post.username, equalTo("Username"))
    }

    @Test
    fun testSubreddit() {
        assertThat(post.subreddit, equalTo("SubredditName"))
    }

    @Test
    fun testPermalink() {
        assertThat(post.permalink, equalTo("/r/permalink"))
    }

    @Test
    fun testDomain() {
        assertThat(post.domain, equalTo("i.redd.it"))
    }

    @Test
    fun testTimestamp() {
        assertThat(post.timestamp, equalTo(1618877142))
    }

    @Test
    fun testCommentsCount() {
        assertThat(post.commentsCount, equalTo(679))
    }

    @Test
    fun testPoints() {
        assertThat(post.points, equalTo(25838))
    }

    @Test
    fun testPreviews_source() {
        val j = JSONObject(res.get("redditapi-post/previews-6.json"))
        val p = StandardPost(j, "after")
        assertThat(p.thumbnailSource?.width, equalTo(748))
        assertThat(p.thumbnailSource?.height, equalTo(701))
        assertThat(p.thumbnailSource?.url, equalTo("preview-url-source"))
    }

    @Test
    fun testPreviews_3Previews() {
        val j = JSONObject(res.get("redditapi-post/previews-3.json"))
        val p = StandardPost(j, "after")
        assertThat(p.thumbnail320?.width, equalTo(320))
        assertThat(p.thumbnail320?.height, equalTo(299))
        assertThat(p.thumbnail320?.url, equalTo("preview-url-320"))
        assertThat(p.thumbnail640, nullValue())
    }

    @Test
    fun testPreviews_4Previews() {
        val j = JSONObject(res.get("redditapi-post/previews-4.json"))
        val p = StandardPost(j, "after")
        assertThat(p.thumbnail640?.width, equalTo(640))
        assertThat(p.thumbnail640?.height, equalTo(599))
        assertThat(p.thumbnail640?.url, equalTo("preview-url-640"))
        assertThat(p.thumbnail960, nullValue())
    }

    @Test
    fun testPreviews_5Previews() {
        val j = JSONObject(res.get("redditapi-post/previews-5.json"))
        val p = StandardPost(j, "after")
        assertThat(p.thumbnail960?.width, equalTo(960))
        assertThat(p.thumbnail960?.height, equalTo(800))
        assertThat(p.thumbnail960?.url, equalTo("preview-url-960"))
    }

    @Test
    fun testContent_selfText() {
        val j = JSONObject(res.get("redditapi-post/content-selftext.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("Self Text"))
        assertThat(p.contentType, equalTo(PostContentType.TEXT))
    }

    @Test
    fun testContent_image() {
        val j = JSONObject(res.get("redditapi-post/content-image.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("image-url"))
        assertThat(p.contentType, equalTo(PostContentType.IMAGE))
    }

    @Test
    fun testContent_link() {
        val j = JSONObject(res.get("redditapi-post/content-link.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.LINK))
    }

    @Test
    fun testContent_hostedVideo() {
        val j = JSONObject(res.get("redditapi-post/content-video-hosted.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("fallback-url"))
        assertThat(p.contentType, equalTo(PostContentType.VIDEO_HOSTED))
    }

    @Test
    fun testContent_richVideo() {
        val j = JSONObject(res.get("redditapi-post/content-video-rich.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.VIDEO_RICH))
    }

    @Test
    fun testContent_other() {
        val j = JSONObject(res.get("redditapi-post/content-other.json"))
        val p = StandardPost(j, "after")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.OTHER))
    }
}