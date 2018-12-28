package com.yyc.yycframe.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.yyc.yycframe.utils.StatusBarUtils;

/**
 * 不包含Dagger的基类
 */
public abstract class BaseSimpleActivity extends AppCompatActivity implements IStatusBar {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
