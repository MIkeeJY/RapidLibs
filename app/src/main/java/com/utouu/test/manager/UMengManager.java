package com.utouu.test.manager;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by 李刚 on 2016/5/13/15:09.
 */
public class UMengManager {

    /**
     * 开始统计（不包含Fragment的Activity）的使用时长
     *
     * @param activity
     */
    public static void startActivityAnalyze(Activity activity) {
        String simpleName = activity.getClass().getSimpleName();
        MobclickAgent.onPageStart(simpleName);
        MobclickAgent.onResume(activity);
    }

    /**
     * 结束统计（不包含Fragment的Activity）的使用时长
     *
     * @param activity
     */
    public static void stopActivityAnalyze(Activity activity) {
        MobclickAgent.onPageEnd(activity.getClass().getSimpleName());
        MobclickAgent.onPause(activity);
    }

    /**
     * 开始统计（包含Fragment的Activity）的使用时长
     *
     * @param activity
     */
    public static void startFragmentActivityAnalyze(Activity activity) {
        MobclickAgent.onResume(activity);
    }

    /**
     * 结束统计（包含Fragment的Activity）的使用时长
     *
     * @param activity
     */
    public static void stopFragmentActivityAnalyze(Activity activity) {
        MobclickAgent.onPause(activity);
    }

    /**
     * 开始统计Fragment
     *
     * @param fragment
     */
    public static void startFragmentAnalyze(Fragment fragment) {
        MobclickAgent.onPageStart(fragment.getClass().getSimpleName());
    }

    /**
     * 结束统计Fragment
     *
     * @param fragment
     */
    public static void stopFragmentAnalyze(Fragment fragment) {
        MobclickAgent.onPageEnd(fragment.getClass().getSimpleName());
    }

    /**
     * 开启账号统计
     *
     * @param platName
     * @param userID
     */
    public static void startAccountAnalyze(String platName, String userID) {
        if (platName.isEmpty()) MobclickAgent.onProfileSignIn(userID);
        else MobclickAgent.onProfileSignIn(platName, userID);
    }

    /**
     * 关闭账号统计
     */
    public static void stopAccountAnalyze() {
        MobclickAgent.onProfileSignOff();
    }


}
