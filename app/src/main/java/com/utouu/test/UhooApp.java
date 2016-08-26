package com.utouu.test;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.marno.mbasiclib.utils.MLog;
import com.marno.mbasiclib.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

/**
 * Created by 李刚 on 2016/8/23/15:22.
 */
public class UhooApp extends Application {


    private static Context mContext;
    public final boolean isDebug = BuildConfig.DEBUG;
    private final String TAG = "marno";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //友盟分析
        MobclickAgent.openActivityDurationTrack(false);//关闭默认的统计方式
        MobclickAgent.setDebugMode(isDebug);//设置debug模式
        MobclickAgent.setCatchUncaughtExceptions(true);//捕捉异常方式

        //关闭shareSDK的日志
//        ShareSDK.closeDebug();

        //Logger
        Logger.init(TAG).methodCount(2);//隐藏线程信息

        ToastUtil.init(mContext);

        //JPush初始化
//        JPushInterface.init(this);
//        JPushInterface.setDebugMode(isDebug);

        //配置realm
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
//        Realm.setDefaultConfiguration(realmConfig);

//        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
//                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
//            //融云SDK的初始化
//            RongIM.init(this);
//        }

        //在debug模式下的配置
        if (isDebug) {
            //检测内存泄漏
            //LeakCanary.install(this);
            //捕捉崩溃信息,手机不在控制台时捕捉bug,当开启后导致控制台无法输出异常日志
            // CrashWoodpecker.fly().to(this);
        } else
            Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());
    }


    //全局获取ApplicationContext
    public static Context getAppContext() {
        return mContext;
    }

    //手动处理异常信息
    class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            MLog.e(e.getMessage());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());

        }
    }

    //获得当前进程的名字
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    //判断APP是否在前台显示
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                MLog.i("UhooApp   >>此appimportace =" + appProcess.importance
                        + ",context.getClass().getName()=" + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    MLog.i("UhooApp   >>处于后台" + appProcess.processName);
                    return true;
                } else {
                    MLog.i("UhooApp   >>处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }
}
