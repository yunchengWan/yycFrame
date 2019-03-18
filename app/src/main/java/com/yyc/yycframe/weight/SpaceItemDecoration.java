package com.yyc.yycframe.weight;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = layoutManager.getChildCount();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            if (spanCount % childCount != 0) {
                //垂直
                outRect.set(0, 0, dp2px(parent.getContext(), mVerticalSpace), 0);
            }
            if (parent.getChildItemId(view) >= spanCount) {
                //水平
                outRect.set(0, 0, 0, dp2px(parent.getContext(), mHorizontalSpace));
            }
        } else {
            if (parent.getChildItemId(view) != 0) {
                outRect.set(0, 0, dp2px(parent.getContext(), mVerticalSpace), dp2px(parent.getContext(), mHorizontalSpace));
            }
        }
    }

    private int dp2px(Context context, int dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }
}
