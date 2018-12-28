package com.yyc.yycframe.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 设置StatusBar文字颜色之类
 */
public class StatusBarUtils {

    public static void setStatusBarDark(@NonNull Activity activity, boolean isDark) {

        //MIUI
        if (OSUtils.isMIUI6Later()) {
            setMIUIStatusBarDarkFont(activity, isDark);
            return;
        }

        //FLYME
        if (OSUtils.isFlymeOS4Later()) {
            setFlyMeStatusBarDarkFont(activity, isDark);
            return;
        }

        //DEFAULT
        setNativeStatusBarDarkFont(activity, isDark);
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * <p>
     * MIUI7以上调用系统
     */
    @SuppressLint("PrivateApi")
    @SuppressWarnings("unchecked")
    private static void setMIUIStatusBarDarkFont(@NonNull Activity activity, boolean isDark) {
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (isDark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && OSUtils.isMIUI7Later()) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    setNativeStatusBarDarkFont(activity, isDark);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     */
    private static void setFlyMeStatusBarDarkFont(@NonNull Activity activity, boolean isDark) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class
                    .getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (isDark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setNativeStatusBarDarkFont(@NonNull Activity activity, boolean isDark) {
        if (activity.getWindow() == null ||
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        View decorView = activity.getWindow().getDecorView();

        if (isDark) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        setFitsSystemWindows(decorView);
    }

    /**
     * 解决顶部与布局重叠问题
     * Sets fits system windows.
     */
    private static void setFitsSystemWindows(View decorView) {
        ViewGroup contentView = decorView.findViewById(android.R.id.content);
        View root = contentView.getChildAt(0);
        if (root instanceof ViewGroup) {
            root.setFitsSystemWindows(true);
            ((ViewGroup) root).setClipToPadding(true);
        }
    }
}
