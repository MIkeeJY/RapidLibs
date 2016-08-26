package com.marno.mbasiclib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;


/**
 * Created by 李刚 on 2016/3/14/16:45.
 * 设备相关的工具类
 */
public class DeviceUtil {


    /**
     * IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
     * IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
     * 其组成为：
     * 1. 前6位数(TAC)是”型号核准号码”，一般代表机型
     * 2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
     * 3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
     * 4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        MLog.i("当前手机IMEI号码>>>" + IMEI);
        return IMEI;
    }

    /**
     * 获取系统的版本编号
     *
     * @return int值，API编号安卓6对应23
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备型号的名字
     */
    public static String getDeviceName() {
        return Build.MODEL;
    }

    /**
     * 获取系统VersionName
     *
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本未知";
        }
    }

    /**
     * 获取系统VersionCode
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

}

