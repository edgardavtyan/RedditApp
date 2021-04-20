package com.ed.redditapp

import com.ed.redditapp.lib.api.standard.StandardSubreddit
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StandardSubredditTest {
    @Test
    fun testName() {
        assertThat(getSubreddit().name, equalTo("name"))
    }

    @Test
    fun testTitle() {
        assertThat(getSubreddit().title, equalTo("title"))
    }

    @Test
    fun testDescription() {
        assertThat(getSubreddit().description, equalTo("description"))
    }

    @Test
    fun testSubsCount() {
        assertThat(getSubreddit().subsCount, equalTo(123))
    }

    @Test
    fun testIcon_imgNull_community() {
        val subreddit = getSubreddit("subreddit-icon-img")
        assertThat(subreddit.iconUrl, equalTo("icon"))
    }

    @Test
    fun testIcon_communityNull_img() {
        val subreddit = getSubreddit("subreddit-icon-community")
        assertThat(subreddit.iconUrl, equalTo("icon"))
    }

    private fun getSubreddit(): StandardSubreddit {
        val path = "redditapi-subreddit/subreddit-icon-img.json"
        val json = JSONObject(ResourceReader().get(path))
        return StandardSubreddit(json)
    }

    private fun getSubreddit(file: String): StandardSubreddit {
        val path = "redditapi-subreddit/$file.json"
        val json = JSONObject(ResourceReader().get(path))
        return StandardSubreddit(json)
    }
}