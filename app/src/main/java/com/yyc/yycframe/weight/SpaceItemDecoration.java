package com.yyc.yycframe.weight;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mVerticalSpace = 0;
    private int mHorizontalSpace = 0;

    public SpaceItemDecoration(int verticalSpace, int horizontalSpace) {
        this.mVerticalSpace = verticalSpace;
        this.mHorizontalSpace = horizontalSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        long viewIndex = parent.getChildAdapterPosition(view);
        int childCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            long spanIndex = viewIndex / spanCount;
            if (spanIndex != 0) {
                outRect.top = dp2px(parent.getContext(), mHorizontalSpace);
            }
            if (viewIndex % spanCount != spanCount - 1) {
                outRect.right = dp2px(parent.getContext(), mVerticalSpace);
            }
        } else if (layoutManager instanceof LinearLayoutManager){
            if (viewIndex != childCount - 1) {
                if (((LinearLayoutManager)layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    outRect.right = dp2px(parent.getContext(), mVerticalSpace);
                } else {
                    outRect.bottom = dp2px(parent.getContext(), mHorizontalSpace);
                }
            }
        }
    }

    private int dp2px(Context context, int dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }
}
