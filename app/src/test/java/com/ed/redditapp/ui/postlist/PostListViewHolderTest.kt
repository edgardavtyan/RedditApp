package com.ed.redditapp.ui.postlist

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ed.redditapp.R
import com.ed.redditapp.databinding.ListitemPostBinding
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.powermock.reflect.Whitebox

@RunWith(MockitoJUnitRunner::class)
class PostListViewHolderTest {
    @Mock lateinit var binding: ListitemPostBinding
    @Mock lateinit var root: LinearLayout
    @Mock lateinit var context: Context

    private lateinit var holder: PostListViewHolder

    @Before
    fun beforeEach() {
        whenever(binding.root).doReturn(root)
        whenever(root.context).doReturn(context)
        holder = PostListViewHolder(binding)
    }

    @Test
    fun setTitle() {
        Whitebox.setInternalState(binding, "title", mock<TextView>())
        holder.setTitle("title")
        verify(binding.title).text = "title"
    }

    @Test
    fun setSubreddit() {
        Whitebox.setInternalState(binding, "subreddit", mock<TextView>())

        holder.setSubreddit("subreddit")

        verify(binding.subreddit).text = "subreddit"
    }

    @Test
    fun setCommentsCount() {
        Whitebox.setInternalState(binding, "comments", mock<TextView>())
        whenever(context.getString(R.string.post_comments_pattern)).doReturn("%s")

        holder.setCommentsCount(123)

        verify(binding.comments).text = "123"
    }

    @Test
    fun setThumbnailGone() {
        Whitebox.setInternalState(binding, "thumbnail", mock<ImageView>())
        holder.setThumbnail(null)
        verify(binding.thumbnail).visibility = View.GONE
    }

    @Test
    fun setThumbnailVisible() {
        Whitebox.setInternalState(binding, "thumbnail", mock<ImageView>())
        holder.setThumbnail(mock())
        verify(binding.thumbnail).visibility = View.VISIBLE
    }

    @Test
    fun setDisplayIconVisible() {
        Whitebox.setInternalState(binding, "topWrapper", mock<LinearLayout>())
        Whitebox.setInternalState(binding, "infoNoIcon", mock<TextView>())
        holder.setDisplayIcon(true)
        verify(binding.topWrapper).visibility = View.VISIBLE
        verify(binding.infoNoIcon).visibility = View.GONE
    }

    @Test
    fun setDisplayIconGone() {
        Whitebox.setInternalState(binding, "topWrapper", mock<LinearLayout>())
        Whitebox.setInternalState(binding, "infoNoIcon", mock<TextView>())
        holder.setDisplayIcon(false)
        verify(binding.topWrapper).visibility = View.GONE
        verify(binding.infoNoIcon).visibility = View.VISIBLE
    }

    @Test
    fun setInfoClickListener() {
        Whitebox.setInternalState(binding, "topWrapper", mock<LinearLayout>())
        val listener = mock<View.OnClickListener>()
        holder.setInfoClickListener(listener)
        verify(binding.topWrapper).setOnClickListener(listener)
    }

    @Test
    fun setTitleClickListener() {
        Whitebox.setInternalState(binding, "title", mock<TextView>())
        val listener = mock<View.OnClickListener>()
        holder.setTitleClickListener(listener)
        verify(binding.title).setOnClickListener(listener)
    }

    @Test
    fun setThumbnailClickListener() {
        Whitebox.setInternalState(binding, "thumbnail", mock<ImageView>())
        val listener = mock<View.OnClickListener>()
        holder.setThumbnailClickListener(listener)
        verify(binding.thumbnail).setOnClickListener(listener)
    }
}