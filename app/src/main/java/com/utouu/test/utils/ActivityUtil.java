package com.utouu.test.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 李刚 on 2016/4/7/13:54.
 * 判断用户是否登录，记录用户登录状态
 */
public class ActivityUtil {

//    /**
//     * 判断是否需要登录才能访问
//     *
//     * @param context
//     * @param activityClass
//     * @param isNeedLogin
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  boolean isNeedLogin) {
//        toActivity(context, activityClass, "", isNeedLogin);
//    }
//
//    /**
//     * 不需要登录就可以访问的页面
//     *
//     * @param context
//     * @param activityClass
//     */
//    public static void toActivity(Context context, Class<? extends Activity> activityClass) {
//        toActivity(context, activityClass, false);
//    }
//
//    /**
//     * 携带一个参数跳转到目标Activity，并判断登录状态
//     *
//     * @param context
//     * @param activityClass
//     * @param arg0
//     * @param isNeedLogin
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  String arg0,
//                                  boolean isNeedLogin) {
//        toActivity(context, activityClass, arg0, null, isNeedLogin);
//    }
//
//    /**
//     * 携带一个参数跳转到目标Activity，无需登录
//     *
//     * @param context
//     * @param activityClass
//     * @param arg0
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  String arg0) {
//        toActivity(context, activityClass, arg0, false);
//    }
//
//    /**
//     * 开启制定Activity，并且传递多个参数，需要登陆
//     *
//     * @param context       上下文
//     * @param activityClass 目标Activity
//     * @param bundle        参数bundle
//     * @param isNeedLogin   是否需要验证登陆状态
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  Bundle bundle,
//                                  boolean isNeedLogin) {
//        toActivity(context, activityClass, "", bundle, isNeedLogin);
//    }
//
//    public static void toActivityForResult(Activity activity,
//                                           Class<? extends Activity> activityClass,
//                                           Bundle bundle,
//                                           int requestCode) {
//        toActivityForResult(activity, activityClass, bundle, requestCode, true);
//    }
//
//    /**
//     * 开启指定Activity，并且传递多个参数，不需要登陆
//     *
//     * @param context       上下文
//     * @param activityClass 目标Activity
//     * @param bundle        参数bundle
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  Bundle bundle) {
//        toActivity(context, activityClass, bundle, false);
//    }
//
//    /**
//     * 开启指定Activity，并且传递多个参数，验证登陆
//     *
//     * @param context       上下文
//     * @param activityClass 目标Activity
//     * @param arg0          单个参数
//     * @param bundle        参数bundle
//     */
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  String arg0,
//                                  Bundle bundle,
//                                  boolean isNeedLogin) {
//        Intent intent;
//        if (isNeedLogin && !isUserLogin(context)) {
//            intent = new Intent(context, LoginActivity.class);
//            //记录下toActivity的全限定名，用于在登陆完成后，跳到对应页面
//            intent.putExtra(ARG_CALSSNAME, activityClass.getName());
//        } else {
//            intent = new Intent(context, activityClass);
//            if (activityClass == AMainActivity.class) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
//        }
//        if (bundle != null) intent.putExtras(bundle);
//        if (!arg0.isEmpty()) intent.putExtra(ARG_0, arg0);
//        context.startActivity(intent);
//    }
//
//    public static void toActivityForResult(Activity activity,
//                                           Class<? extends Activity> activityClass,
//                                           Bundle bundle,
//                                           int requestCode,
//                                           boolean isNeedLogin) {
//        Intent intent;
//        if (isNeedLogin && !isUserLogin(activity)) {
//            intent = new Intent(activity, LoginActivity.class);
//            //记录下toActivity的全限定名，用于在登陆完成后，跳到对应页面
////            intent.putExtra(ARG_CALSSNAME, activityClass.getName());
//        } else {
//            intent = new Intent(activity, activityClass);
//            if (activityClass == AMainActivity.class) {
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            }
//        }
//        if (bundle != null) intent.putExtras(bundle);
//        activity.startActivityForResult(intent, requestCode);
//    }
//
//
//    public static void toActivity(Context context,
//                                  String scheme,
//                                  boolean isNeedLogin) {
//        MLog.i("scheme>>>" + scheme);
//        if (TextUtils.isEmpty(scheme)) {
//            ToastUtil.showToast("scheme为空");
//            return;
//        }
//        Intent intent;
//        if (isNeedLogin && !isUserLogin(context)) {
//            intent = new Intent(context, LoginActivity.class);
//        } else {
//            intent = new Intent();
//            intent.setAction(C.CUSTOM_ACTION);
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.setData(Uri.parse(scheme));
//        }
//        try {
//            context.startActivity(intent);
//        } catch (Exception e) {
//            ToastUtil.showToast("不支持的scheme类型");
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void toActivity(Context context,
//                                  Class<? extends Activity> activityClass,
//                                  String arg0,
//                                  Bundle bundle) {
//        toActivity(context, activityClass, arg0, bundle, false);
//    }

    public static void to(Context context, Class<? extends Activity> activityClass) {
        context.startActivity(new Intent(context, activityClass));
    }
}
