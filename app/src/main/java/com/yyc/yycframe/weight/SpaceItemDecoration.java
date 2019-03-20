package com.yyc.yycframe.weight;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mVerticalSpace;
    private int mHorizontalSpace;

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
        int viewIndex = parent.getChildAdapterPosition(view);
        int childCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager)layoutManager).getOrientation();
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int spanIndex = ((GridLayoutManager) layoutManager).getSpanSizeLookup().getSpanGroupIndex(viewIndex, spanCount);

            int perSpaceWidth_H = dp2px(parent.getContext(), mHorizontalSpace) * (spanCount - 1) / spanCount;
            int perSpaceWidth_V = dp2px(parent.getContext(), mVerticalSpace) * (spanCount - 1) / spanCount;

            int left = 0;
            int right = 0;
            int top = 0;
            int bottom = 0;

            if (orientation == GridLayoutManager.VERTICAL) {
                //垂直
                if (spanIndex != 0) {
                    top = dp2px(parent.getContext(), mHorizontalSpace);
                }
                left = viewIndex % spanCount * (dp2px(parent.getContext(), mVerticalSpace) - perSpaceWidth_V);
                right = perSpaceWidth_V - left;
            } else {
                //水平
                if (spanIndex != 0) {
                    left = dp2px(parent.getContext(), mHorizontalSpace);
                }
                top = viewIndex % spanCount * (dp2px(parent.getContext(), mHorizontalSpace) - perSpaceWidth_H);
                bottom = perSpaceWidth_H - top;
            }

            outRect.set(left, top, right, bottom);
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
