package com.ed.redditapp.ui.subreddit;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirstItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private final int offset;

    public FirstItemOffsetDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(
            @NonNull Rect outRect,
            @NonNull View view,
            RecyclerView parent,
            @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = offset;
        }
    }
}
