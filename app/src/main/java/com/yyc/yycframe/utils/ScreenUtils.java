package com.yyc.yycframe.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by yuncheng.wan on 2018/7/3.
 * <p>
 * 说明：屏幕相关工具类
 */

public class ScreenUtils {
    /**
     * 获取状态栏高度 单位px
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getStatusHeight(@NonNull Context context) {
        int height = 0;
        //API 19以上才能获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int resourceId = context.getResources()
                    .getIdentifier(
                            "status_bar_height",
                            "dimen",
                            "android"
                    );
            if (resourceId > 0) {
                height = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        if (height == 0) {
            //上面方式拿不到就通过反射来取
            height = getStatusBarHeight(context);
        }
        return height;
    }

    /**
     * 获取屏幕高度 单位px
     */
    public static int getScreenHeight(@NonNull Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度 单位px
     */
    public static int getScreenWidth(@NonNull Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 判断navigation是否显示
     */
    @SuppressLint("ObsoleteSdkInt")
    public static boolean hasNavigationBar(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !(menu || back);
        }
    }

    /**
     * 获取NavigationBar高度 单位px
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getNavigationBarHeight(@NonNull Activity activity) {
        int height = 0;
        if (!hasNavigationBar(activity)) {
            return height;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int resourceId = activity.getResources()
                    .getIdentifier(
                            "navigation_bar_height",
                            "dimen",
                            "android"
                    );
            if (resourceId > 0) {
                height = activity.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return height;
    }

    /**
     * dp 转 px
     */
    public static int dp2px(@NonNull Context context, float dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * px 转 dp
     */
    public static int px2dp(@NonNull Context context, float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 通过反射获得status bar 高度
     */
    @SuppressLint("PrivateApi")
    private static int getStatusBarHeight(@NonNull Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
