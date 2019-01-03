package com.yyc.yycframe.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yyc.yycframe.weight.ITitleBar;
import com.yyc.yycframe.weight.TitleBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseSimpleFragment extends Fragment {
    private final static int DEFAULT_TITLE_HEIGHT = 48; //dp

    private Unbinder mUnbinder;

    protected ITitleBar mTitleBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        if (useDefaultTitleBar()) {
            rootView = initRootViewWithTitleBar(inflater);
        } else {
            rootView = inflater.inflate(layout(), container);
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        initData();
        initView();
        return rootView;
    }

    @LayoutRes
    protected abstract int layout();

    protected abstract void initData();

    protected abstract void initView();

    protected boolean useDefaultTitleBar() {
        return false;
    }

    @NonNull
    private View initRootViewWithTitleBar(LayoutInflater inflater) {
        FrameLayout.LayoutParams rootViewPara = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout rootView = new LinearLayout(getContext());
        rootView.setLayoutParams(rootViewPara);

        rootView.setOrientation(LinearLayout.VERTICAL);
        mTitleBar = new TitleBarView(getContext());
        LinearLayout.LayoutParams titleBarPara = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleBarPara.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TITLE_HEIGHT, getResources().getDisplayMetrics());
        rootView.addView((View) mTitleBar, titleBarPara);

        inflater.inflate(layout(), rootView, true);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
