package com.marno.mbasiclib.utils;

import com.marno.mbasiclib.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by 李刚 on 2016/4/18/14:33.
 * 日志辅助工具
 */
public abstract class MLog {

    private MLog() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final boolean isDebug = BuildConfig.DEBUG;

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Logger.i(msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Logger.e(msg);
    }

    public static void json(String jsonStr) {
        if (isDebug)
            Logger.json(jsonStr);
    }

    // 下面四个是自定义tag的函数
    public static void i(String tag, String msg) {
        i(tag + " >>> " + msg);
    }

    public static void d(String tag, String msg) {
        d(tag + " >>> " + msg);
    }

    public static void e(String tag, String msg) {
        e(tag + " >>> " + msg);
    }

    public static void json(String tag, String jsonStr) {
        json(tag + " >>> " + jsonStr);
    }
}
