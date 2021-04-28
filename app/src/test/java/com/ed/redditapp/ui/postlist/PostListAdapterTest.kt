package com.ed.redditapp.ui.postlist

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.lib.api.PostThumbnail
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class PostListAdapterTest {
    @Mock lateinit var holder: PostListViewHolder

    @Spy val adapter = PostListAdapter()

    @Before
    fun beforeEach() {
        doNothing().whenever(adapter).notifyDataSetChanged()
        doNothing().whenever(adapter).notifyItemRangeInserted(any(), any())
        doNothing().whenever(adapter).notifyItemChanged(any(), any())
    }

    @Test
    fun bindViewHolder() {
        val thumbnail = mock<PostThumbnail>()
        val post = mock<Post> {
            on { title } doReturn "title"
            on { username } doReturn "username"
            on { subreddit } doReturn "subreddit"
            on { subredditIconUrl } doReturn "iconUrl"
            on { commentsCount } doReturn 123
            on { points } doReturn 234
            on { timestamp } doReturn 345
            on { largestThumbnail } doReturn thumbnail
        }
        val posts = Array(1) { post }

        adapter.updateData(posts)
        adapter.onBindViewHolder(holder, 0)

        verify(holder).setTitle("title")
        verify(holder).setCommentsCount(123)
        verify(holder).setInfo(234, 345, "username")
        verify(holder).setThumbnail(thumbnail)
        verify(holder).setSubreddit("subreddit")
        verify(holder).setSubredditIcon("iconUrl")
    }

    @Test
    fun invokeListener_scrolledNearEnd() {
        val posts = Array<Post>(10) { mock() }
        val nearEndListener = mock<PostListNearEndListener>()

        adapter.nearEndListener = nearEndListener
        adapter.updateData(posts)
        adapter.onBindViewHolder(holder, 2)

        verify(nearEndListener).invoke(any())
    }

    @Test
    fun bindViewHolder_payloads() {
        val post = mock<Post> { on { subredditIconUrl } doReturn "iconUrl" }
        val posts = Array(1) { post }
        val payloads = MutableList<Any>(1) { mock() }

        adapter.updateData(posts)
        adapter.onBindViewHolder(holder, 0, payloads)

        verify(holder).setSubredditIcon("iconUrl")
    }

    @Test
    fun bindViewHolder_emptyPayloads() {
        adapter.updateData(Array(1) {mock()})
        adapter.onBindViewHolder(holder, 0, MutableList(0) {})
        verify(adapter).onBindViewHolder(holder, 0)
    }

    @Test
    fun setDisplayIcon() {
        val posts = Array(1) { mock<Post>() }
        adapter.updateData(posts)

        adapter.isDisplayIcon = true
        adapter.onBindViewHolder(holder, 0)

        verify(holder).setDisplayIcon(true)
    }

    @Test
    fun setIcon() {
        val post = mock<Post>()
        val posts = Array(1) { post }

        adapter.updateData(posts)
        adapter.updateIcon("url", 0)

        verify(posts[0]).subredditIconUrl = "url"
        verify(adapter).notifyItemChanged(0, 1)
    }

    @Test
    fun returnFirstViewType() {
        val type = adapter.getItemViewType(0)
        assertThat(type, equalTo(1))
    }

    @Test
    fun returnDefaultViewType() {
        val type = adapter.getItemViewType(1)
        assertThat(type, equalTo(0))
    }

    @Test
    fun returnDefaultItemCount() {
        assertThat(adapter.itemCount, equalTo(0))
    }

    @Test
    fun returnUpdatedItemCount() {
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>(); }
        adapter.updateData(posts)
        assertThat(adapter.itemCount, equalTo(posts.size))
    }

    @Test
    fun addPosts() {
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>(); }
        val newPosts = Array(2) { mock<Post>(); mock<Post>(); }

        adapter.updateData(posts)
        adapter.addPosts(newPosts)

        assertThat(adapter.itemCount, equalTo(5))
        verify(adapter).notifyItemRangeInserted(2, 2)
    }
}