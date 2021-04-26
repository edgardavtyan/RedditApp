package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.testutil.ResourceReader
import com.ed.redditapp.lib.api.PostContentType
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StandardPostTest {
    @Test
    fun testAfter() {
        val p = getPost("content-other")
        assertThat(p.after, equalTo("after"))
    }

    @Test
    fun testTitle() {
        val p = getPost("content-other")
        assertThat(p.title, equalTo("PostTitle"))
    }

    @Test
    fun testUsername() {
        val p = getPost("content-other")
        assertThat(p.username, equalTo("Username"))
    }

    @Test
    fun testSubreddit() {
        val p = getPost("content-other")
        assertThat(p.subreddit, equalTo("SubredditName"))
    }

    @Test
    fun testPermalink() {
        val p = getPost("content-other")
        assertThat(p.permalink, equalTo("/r/permalink"))
    }

    @Test
    fun testDomain() {
        val p = getPost("content-other")
        assertThat(p.domain, equalTo("i.redd.it"))
    }

    @Test
    fun testTimestamp() {
        val p = getPost("content-other")
        assertThat(p.timestamp, equalTo(1618877142))
    }

    @Test
    fun testCommentsCount() {
        val p = getPost("content-other")
        assertThat(p.commentsCount, equalTo(679))
    }

    @Test
    fun testPoints() {
        val p = getPost("content-other")
        assertThat(p.points, equalTo(25838))
    }

    @Test
    fun testPreviews_source() {
        val p = getPost("previews-3")
        assertThat(p.thumbnailSource?.width, equalTo(748))
        assertThat(p.thumbnailSource?.height, equalTo(701))
        assertThat(p.thumbnailSource?.url, equalTo("preview-url-source"))
    }

    @Test
    fun testPreviews_3Previews() {
        val p = getPost("previews-3")
        assertThat(p.thumbnail320?.width, equalTo(320))
        assertThat(p.thumbnail320?.height, equalTo(299))
        assertThat(p.thumbnail320?.url, equalTo("preview-url-320"))
        assertThat(p.thumbnail640, nullValue())
    }

    @Test
    fun testPreviews_4Previews() {
        val p = getPost("previews-4")
        assertThat(p.thumbnail640?.width, equalTo(640))
        assertThat(p.thumbnail640?.height, equalTo(599))
        assertThat(p.thumbnail640?.url, equalTo("preview-url-640"))
        assertThat(p.thumbnail960, nullValue())
    }

    @Test
    fun testPreviews_5Previews() {
        val p = getPost("previews-5")
        assertThat(p.thumbnail960?.width, equalTo(960))
        assertThat(p.thumbnail960?.height, equalTo(800))
        assertThat(p.thumbnail960?.url, equalTo("preview-url-960"))
    }

    @Test
    fun testContent_selfText() {
        val p = getPost("content-selftext")
        assertThat(p.content, equalTo("Self Text"))
        assertThat(p.contentType, equalTo(PostContentType.TEXT))
    }

    @Test
    fun testContent_image() {
        val p = getPost("content-image")
        assertThat(p.content, equalTo("image-url"))
        assertThat(p.contentType, equalTo(PostContentType.IMAGE))
    }

    @Test
    fun testContent_link() {
        val p = getPost("content-link")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.LINK))
    }

    @Test
    fun testContent_hostedVideo() {
        val p = getPost("content-video-hosted")
        assertThat(p.content, equalTo("fallback-url"))
        assertThat(p.contentType, equalTo(PostContentType.VIDEO_HOSTED))
    }

    @Test
    fun testContent_richVideo() {
        val p = getPost("content-video-rich")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.VIDEO_RICH))
    }

    @Test
    fun testContent_other() {
        val p = getPost("content-other")
        assertThat(p.content, equalTo("link-url"))
        assertThat(p.contentType, equalTo(PostContentType.OTHER))
    }

    private fun getPost(file: String): StandardPost {
        val j = JSONObject(ResourceReader().get("redditapi-post/$file.json"))
        return StandardPost(j, "after")
    }
}