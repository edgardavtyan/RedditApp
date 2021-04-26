package com.ed.redditapp.ui.main

import com.ed.redditapp.lib.api.Post
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class MainModelTest {
    @Mock lateinit var postsAsyncTask: MainPostsAsyncTask
    @Mock lateinit var iconUrlAsyncTask: MainSubredditIconUrlAsyncTask

    @Test
    fun getMainPagePosts() {
        val model = MainModel(postsAsyncTask, iconUrlAsyncTask)
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>() }
        val callback = mock<MainPostsCallback>()

        model.getMainPagePosts(callback)

        argumentCaptor<MainPostsCallback>().apply {
            verify(postsAsyncTask).run(eq(null), capture())
            firstValue(posts)
            verify(callback)(posts)
        }
    }

    @Test
    fun getNextPosts() {
        val model = MainModel(postsAsyncTask, iconUrlAsyncTask)
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>() }
        val callback = mock<MainPostsCallback>()

        model.getNextPosts("after", callback)

        argumentCaptor<MainPostsCallback>().apply {
            verify(postsAsyncTask).run(eq("after"), capture())
            firstValue(posts)
            verify(callback)(posts)
        }
    }

    @Test
    fun getSubredditIcons() {
        val model = MainModel(postsAsyncTask, iconUrlAsyncTask)
        val callback = mock<MainSubredditIconUrlCallback>()

        model.getMainPageSubredditIcons(callback)

        argumentCaptor<MainSubredditIconUrlCallback>().apply {
            verify(iconUrlAsyncTask).run(any(), capture())
            firstValue("url", 123)
            verify(callback)("url", 123)
        }
    }
}