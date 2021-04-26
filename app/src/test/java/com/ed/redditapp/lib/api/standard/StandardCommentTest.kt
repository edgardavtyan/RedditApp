package com.ed.redditapp.lib.api.standard

import com.ed.redditapp.testutil.ResourceReader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StandardCommentTest {
    private lateinit var comment: StandardComment

    @Before
    fun before() {
        val path = "redditapi-comment/comment.json"
        val json = JSONObject(ResourceReader().get(path))
        val jsonComment = StandardJsonComment(json, 3)
        comment = StandardComment(jsonComment)
    }

    @Test
    fun testUsername() {
        assertThat(comment.username, equalTo("username"))
    }

    @Test
    fun testBody() {
        assertThat(comment.body, equalTo("body"))
    }

    @Test
    fun testTimestamp() {
        assertThat(comment.timestamp, equalTo(123))
    }

    @Test
    fun testPoints() {
        assertThat(comment.points, equalTo(234))
    }

    @Test
    fun testIndent() {
        assertThat(comment.indent, equalTo(3))
    }
}