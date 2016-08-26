package com.marno.mbasiclib.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marno.mbasiclib.R;

/**
 * Created by 李刚 on 2016/1/19.
 * 统一管理Toast
 */
public class ToastUtil {

    public static final String ExceptionRemind = "You have to call static method init() first in Applicaiton";

    public static Context mContext;
    private static Toast mCustomToast;
    private static Toast mSystemToast;

    private static int CENTER = Gravity.CENTER;
    private static int CENTER_TOP = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
    private static int CENTER_BOTTOM = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private static int TOP_LEFT = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;

    public static void init(Context context) {
        mContext = context;
    }


    /**
     * 显示系统默认Toast
     *
     * @param content
     */
    public static void systemToast(String content) {
        if (mContext == null)
            throw new NullPointerException(ExceptionRemind);

        int duration = Toast.LENGTH_SHORT;
        if (content.length() > 10) duration = Toast.LENGTH_LONG;

        if (mSystemToast == null) {
            mSystemToast = Toast.makeText(mContext, content, duration);
        } else {
            mSystemToast.setText(content);
            mSystemToast.setDuration(duration);
        }
        mSystemToast.show();
    }


    private static void show(int icon, int bgColor, int gravity, int xOffset, int yOffiset, String content) {
        if (mContext == null)
            throw new NullPointerException(ExceptionRemind);

        int duration = content.length() > 10 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_common_toast, null);
        TextView tvToast = (TextView) view.findViewById(R.id.tv_content_toast);
        LinearLayout llayoutBg = (LinearLayout) view.findViewById(R.id.llayout_bg_toast);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon_toast);

        llayoutBg.setBackgroundResource(bgColor);
        ivIcon.setImageResource(icon);
        tvToast.setText(content);

        if (mCustomToast == null) mCustomToast = new Toast(mContext);

        mCustomToast.setGravity(gravity, xOffset, yOffiset);
        mCustomToast.setView(view);
        mCustomToast.setDuration(duration);
        mCustomToast.show();
    }

    /**
     * 在屏幕中间显示toast
     *
     * @param icon    toast图标
     * @param bgColor toast背景色
     * @param content toast内容
     */
    public static void showAtCenter(int icon, int bgColor, String content) {
        show(icon, bgColor, CENTER, 0, 0, content);
    }

    /**
     * 在屏幕底部中间显示toast
     *
     * @param icon    toast图标
     * @param bgColor toast背景色
     * @param content toast内容
     */
    public static void showAtCenterBottom(int icon, int bgColor, String content) {
        show(icon, bgColor, CENTER_BOTTOM, 0, ScreenUtil.dp2px(96, mContext), content);
    }

    /**
     * 在屏幕中间顶部显示toast
     *
     * @param icon    toast图标
     * @param bgColor toast背景色
     * @param content toast内容
     */
    public static void showAtCenterTop(int icon, int bgColor, String content) {
        show(icon, bgColor, CENTER_TOP, 0, -ScreenUtil.dp2px(96, mContext), content);
    }

    /**
     * 在指定位置显示toast
     *
     * @param icon     toast图标
     * @param bgColor  toast背景色
     * @param xOffset  屏幕x坐标
     * @param yOffiset 屏幕y坐标
     * @param content  toast内容
     */
    public static void showAtPosition(int icon, int bgColor, int xOffset, int yOffiset, String content) {
        show(icon, bgColor, TOP_LEFT, xOffset, yOffiset, content);
    }

    /**
     * 显示成功的toast
     */
    public static void success(String content) {
        showAtCenterBottom(R.drawable.success, R.color.toast_success, content);
    }

    /**
     * 显示错误
     *
     * @param content
     */
    public static void error(String content) {
        showAtCenterBottom(R.drawable.fail, R.color.toast_fail, content);
    }

    /**
     * 显示警告
     *
     * @param content
     */
    public static void warn(String content) {
        showAtCenterBottom(R.drawable.warn, R.color.toast_warn, content);
    }

    /**
     * 显示提示
     *
     * @param content
     */
    public static void remind(String content) {
        showAtCenterBottom(R.drawable.remind, R.color.toast_remind, content);
    }

    /**
     * 显示正常toast
     *
     * @param content
     */
    public static void common(String content) {
        showAtCenterBottom(R.drawable.common, R.color.toast_common, content);
    }

}
