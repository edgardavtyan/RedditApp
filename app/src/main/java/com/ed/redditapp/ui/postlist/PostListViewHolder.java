package com.ed.redditapp.ui.postlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ListitemPostBinding;

import java.util.Calendar;

public class PostListViewHolder extends RecyclerView.ViewHolder {
    private final ListitemPostBinding binding;
    private final Context context;

    public PostListViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ListitemPostBinding.bind(itemView);
        context = itemView.getContext();
    }

    public void setTitle(String title) {
        binding.title.setText(title);
    }

    public void setInfo(int points, long timeEpoch, String username) {
        String niceDateStr = DateUtils.getRelativeTimeSpanString(
                timeEpoch * 1000 ,
                Calendar.getInstance().getTimeInMillis(),
                DateUtils.MINUTE_IN_MILLIS).toString();

        String pattern = context.getString(R.string.post_info_pattern);
        String post = String.format(pattern, points, niceDateStr, username);
        binding.info.setText(post);
    }

    public void setCommentsCount(int commentsCount) {
        String pattern = context.getString(R.string.post_comments_pattern);
        binding.comments.setText(String.format(pattern, commentsCount));
    }

    public void setThumbnail(PostThumbnail thumbnail) {
        if (thumbnail == null) {
            return;
        }

        Bitmap b = Bitmap.createBitmap(thumbnail.getWidth(), thumbnail.getHeight(), Bitmap.Config.ARGB_8888);
        Drawable d = new BitmapDrawable(context.getResources(), b);
        Glide.with(context)
                .load(Html.fromHtml(thumbnail.getUrl()).toString())
                .placeholder(d)
                .into(binding.thumbnail);
    }
}
