package com.marno.mbasiclib.utils;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by marno on 2016/4/24/21:56.
 * Activity回退栈管理
 */
public class StackUtil {

    private final String TAG = this.getClass().getSimpleName();
    private static Stack<Activity> activityStack;

    private static volatile StackUtil instance;

    private StackUtil() {
    }

    public static StackUtil getIns() {
        if (instance == null) {
            synchronized (StackUtil.class) {
                if (instance == null) {
                    instance = new StackUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取当前栈顶Activity
     * @return
     */
    public Activity current() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        Log.i(TAG, "get current activity:" + activity.getClass().getSimpleName());
        return activity;
    }

    /**
     * 入栈
     * @param activity
     */
    public void push(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        Log.i(TAG, "push stack activity:" + activity.getClass().getSimpleName());
        activityStack.add(activity);
    }

    /**
     * 出栈
     * @param activity
     */
    public void pop(Activity activity) {
        if (activity != null) {
            activity.finish();
            Log.i(TAG, "remove current activity:" + activity.getClass().getSimpleName());
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 除了当前之外的Activity出栈
     * @param cls
     */
    public void popAllExceptCurrent(Class cls) {
        while (true) {
            Activity activity = current();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            pop(activity);
        }
    }

    /**
     * 所有Activity出栈
     */
    public void popAll() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = current();
                if (activity == null) break;
                pop(activity);
            }
        }
    }

    /**
     * 获取当前栈中栈顶的第二个Activity
     */
    public Activity getPreviousActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.get(activityStack.size() - 2);
        Log.i(TAG, "get Previous Activity:" + activity.getClass().getSimpleName());
        return activity;
    }
}


