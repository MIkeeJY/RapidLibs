package com.marno.mbasiclib.utils;

import android.content.Context;

/**
 * Created by 李刚 on 2016/8/25/10:47.
 * 屏幕相关工具类
 */
public class ScreenUtil {
    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 将dp转化为px
     *
     * @return dp对应的px值
     */
    public static int dp2px(int dp, Context context) {
        return (int) (dp * (context.getResources().getDisplayMetrics().density) + 0.5);
    }

    /**
     * 将px转化为dp
     *
     * @return px值对应的dp值
     */
    public static int px2dp(int px, Context context) {
        return (int) (px / (context.getResources().getDisplayMetrics().density) + 0.5);
    }
}
