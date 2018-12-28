package com.yyc.yycframe.base;

import android.support.annotation.ColorRes;

/**
 * StatusBar操作相关接口
 */
public interface IStatusBar {

    /**
     * 设置StatusBar主题
     *
     * @param isDark false -> Light / true -> Dark
     */
    void setStatusBarDark(boolean isDark);

    /**
     * 设置StatusBar颜色
     *
     * @param color color String
     */
    void setStatusBarColor(String color);

    /**
     * 设置StatusBar颜色
     *
     * @param color color int
     */
    void setStatusBarColor(@ColorRes int color);
}
