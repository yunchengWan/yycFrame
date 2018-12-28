package com.yyc.yycframe.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class AndroidUtils {

    /**
     * 跳转应用设置界面
     */
    public static void gotoSetting(Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);

            localIntent.setClassName("com.android.settings",
                    "com.android.settings.InstalledAppDetails");

            localIntent.putExtra("com.android.settings.ApplicationPkgName",
                    activity.getPackageName());
        }
        activity.startActivity(localIntent);
    }
}
