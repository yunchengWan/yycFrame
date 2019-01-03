package com.yyc.yycframe.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yyc.yycframe.utils.StatusBarUtils;
import com.yyc.yycframe.weight.ITitleBar;
import com.yyc.yycframe.weight.TitleBarView;

import butterknife.ButterKnife;

/**
 * 不包含Dagger的基类
 */
public abstract class BaseSimpleActivity extends AppCompatActivity implements IStatusBar {

    private final static int DEFAULT_TITLE_HEIGHT = 48; //dp
    protected ITitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init TitleBar
        if (useDefaultTitleBar()) {
            setContentViewWithTitleBar();
        } else {
            setContentView(layout());
        }
        //ButterKnifeBind
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @LayoutRes
    protected abstract int layout();

    protected abstract void initData();

    protected abstract void initView();

    protected boolean useDefaultTitleBar() {
        return true;
    }

    @Override
    public void setStatusBarDark(boolean isDark) {
        StatusBarUtils.setStatusBarDark(this, isDark);
    }

    @Override
    public void setStatusBarColor(String color) {
        getWindow().setStatusBarColor(Color.parseColor(color));
    }

    @Override
    public void setStatusBarColor(@ColorRes int color) {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, color));
    }

    private void setContentViewWithTitleBar() {
        FrameLayout.LayoutParams rootViewPara = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout rootView = new LinearLayout(this);
        addContentView(rootView, rootViewPara);

        rootView.setOrientation(LinearLayout.VERTICAL);
        mTitleBar = new TitleBarView(this);
        LinearLayout.LayoutParams titleBarPara = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleBarPara.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TITLE_HEIGHT, getResources().getDisplayMetrics());
        rootView.addView((View) mTitleBar, titleBarPara);

        LayoutInflater.from(this).inflate(layout(), rootView, true);
    }
}
