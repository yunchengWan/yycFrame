package com.yyc.yycframe.utils;

import android.app.Activity;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationUtils {
    /**
     * 通知权限是否打开 适用8.0
     */

    public static boolean areNotificationsEnabled(Activity activity) {
        return NotificationManagerCompat.from(activity).areNotificationsEnabled();
    }
}
