package com.yyc.yycframe;

import android.util.Log;

public class LogUtil {
    private static final boolean printLog = BuildConfig.DEBUG;

    public static void v(String tag, String message){
        if (printLog) {
            Log.v(tag, message);
        }
    }

    public static void i(String tag, String message){
        if (printLog) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message){
        if (printLog) {
            Log.w(tag, message);
        }
    }

    public static void d(String tag, String message){
        if (printLog) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message){
        if (printLog) {
            Log.e(tag, message);
        }
    }
}
