package com.marno.mbasiclib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Marno on 2016/3/24/15:13.
 * 键盘管理类
 */
public class KeyBoardUtil {

    private static KeyBoardUtil instance;
    private InputMethodManager mInputMethodManager;
    private static Activity mActivity;

    private KeyBoardUtil() {
        mInputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static KeyBoardUtil getInstance(Activity activity) {
        mActivity = activity;
        if (instance == null) {
            instance = new KeyBoardUtil();
        }
        return instance;
    }

    /**
     * 强制显示输入法
     */
    public void show() {
        show(mActivity.getWindow().getCurrentFocus());
    }

    public void show(View view) {
        mInputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 强制关闭输入法
     */
    public void hide() {
        hide(mActivity.getWindow().getCurrentFocus());
    }

    public void hide(View view) {
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 如果输入法已经显示，那么就隐藏它；如果输入法现在没显示，那么就显示它
     */
    public void showOrHide() {
        mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
