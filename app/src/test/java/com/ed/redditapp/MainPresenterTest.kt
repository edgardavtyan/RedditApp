package com.ed.redditapp

import com.ed.redditapp.lib.api.Post
import com.ed.redditapp.ui.main.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner.Silent::class)
class MainPresenterTest {
    @Mock lateinit var view: MainActivity
    @Mock lateinit var model: MainModel

    @Test
    fun showPostsWhenActivityLoaded() {
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>() }
        val presenter = MainPresenter(view, model)
        presenter.onActivityLoaded()

        argumentCaptor<MainPostsCallback>().apply {
            verify(model).getMainPagePosts(capture())
            firstValue(posts)
            verify(view).updatePosts(posts)
        }

        argumentCaptor<MainSubredditIconUrlCallback>().apply {
            verify(model).getMainPageSubredditIcons(capture())
            firstValue("url", 3)
            verify(view).updateSubredditIcons("url", 3)
        }
    }

    @Test
    fun goToSubredditWhenInfoClicked() {
        val presenter = MainPresenter(view, model)
        val post = mock<Post> { on { subreddit }.doReturn("subreddit") }
        presenter.onPostInfoClicked(post)
        verify(view).gotoSubreddit("subreddit")
    }

    @Test
    fun goToPostDetailWhenTitleClicked() {
        val presenter = MainPresenter(view, model)
        val post = mock<Post>()
        presenter.onPostTitleClicked(post)
        verify(view).gotoPostDetail(post)
    }

    @Test
    fun showImageWhenClicked() {
        val presenter = MainPresenter(view, model)
        val post = mock<Post>()
        presenter.onThumbnailClicked(post)
        verify(view).gotoImageView(post)
    }

    @Test
    fun addNewPostsWhenScrolledToEnd() {
        val post = mock<Post> { on { after }.doReturn("after") }
        val posts = Array(3) { mock<Post>(); mock<Post>(); mock<Post>() }
        val presenter = MainPresenter(view, model)
        presenter.onNearEndReached(post)

        argumentCaptor<MainPostsCallback>().apply {
            verify(model).getNextPosts(eq("after"), capture())
            firstValue(posts)
            verify(view).addNextPosts(posts)
        }
    }
}
