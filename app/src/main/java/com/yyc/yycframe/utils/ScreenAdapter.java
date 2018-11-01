package com.yyc.yycframe.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class ScreenAdapter {

    private static float sNoncompatDensity = 0;
    private static float sNoncompatScaledDensity = 0;

    /**
     * 设计图上尺寸
     */
    private static final int sUiSize = 360;

    /**
     * 今日头条适配方案
     * 通过设置对应的density来适配屏幕
     */
    public static void setup(@NonNull final Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNoncompatDensity == 0) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        setDensity(appDisplayMetrics);
        listenActivity(application);
    }

    /**
     * 不需要适配的页面调用
     * <p>
     * setContentView之前调用
     */
    public static void excludeActivity(@NonNull Activity activity) {
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        if (sNoncompatDensity != 0) {
            activityDisplayMetrics.density = sNoncompatDensity;
            activityDisplayMetrics.scaledDensity = sNoncompatScaledDensity;
            activityDisplayMetrics.densityDpi = (int) (sNoncompatDensity * 160);
        }
    }

    /**
     * 计算Density
     */
    private static void setDensity(DisplayMetrics displayMetrics) {

        if (sNoncompatDensity == 0) {
            //没有调用setup进行初始化
            return;
        }

        final float targetDensity = displayMetrics.widthPixels / sUiSize;
        final float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        final int targetDensityDpi = (int) targetDensity * 160;

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaleDensity;
        displayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * 监听Activity创建等 省去需要在所有activity OnCreate调用步骤
     */
    private static void listenActivity(@NonNull Application application) {
        application.registerActivityLifecycleCallbacks(
                new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        if (activity != null) {
                            DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
                            setDensity(activityDisplayMetrics);
                        }
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                }
        );
    }
}
