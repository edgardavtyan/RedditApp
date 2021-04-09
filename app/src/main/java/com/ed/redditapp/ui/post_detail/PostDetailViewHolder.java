package com.ed.redditapp.ui.post_detail;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ed.redditapp.R;
import com.ed.redditapp.databinding.ListitemCommentBinding;

import java.util.Calendar;

public class PostDetailViewHolder extends RecyclerView.ViewHolder {
    private final ListitemCommentBinding binding;
    private final Context context;

    public PostDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ListitemCommentBinding.bind(itemView);
        context = itemView.getContext();
    }

    public void setInfo(int points, long timestamp, String username) {
        String niceDateStr = DateUtils.getRelativeTimeSpanString(
                timestamp * 1000,
                Calendar.getInstance().getTimeInMillis(),
                DateUtils.MINUTE_IN_MILLIS).toString();

        String pattern = context.getString(R.string.comment_info_pattern);
        String info = String.format(pattern, points, niceDateStr, username);
        binding.user.setText(Html.fromHtml(info));
    }

    public void setBody(String body) {
        binding.body.setHtml(body);
    }

    public void setIndent(int indent) {
        int px = indent * 3 * context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT;
        binding.getRoot().setTranslationX(px);
    }
}
