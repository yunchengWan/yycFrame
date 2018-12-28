package com.yyc.yycframe.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.yyc.yycframe.utils.StatusBarUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * 包含Dagger框架的基类
 * 需要在{@link com.yyc.yycframe.di.ActivityBindingModule}里面声明Activity
 * 需要在{@link com.yyc.yycframe.di.PresenterBindingModule} 里面绑定Presenter
 */
public abstract class BaseActivity<T extends IPresenter> extends DaggerAppCompatActivity implements IStatusBar {

    @Inject
    @Presenter
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterManager.injectActivity(this);
        setContentView(layout());
        //ButterKnifeBind
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @LayoutRes
    protected abstract int layout();

    protected abstract void initData();

    protected abstract void initView();

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
}
