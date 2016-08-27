package com.utouu.test.base;

import android.os.Bundle;

import com.marno.mbasiclib.base.activity.MBasicActivity;
import com.marno.mbasiclib.widgets.TintStatusBar;

/**
 * Created by Marno on 2016/8/25/15:15.
 * 所有Activity的基类
 */
public abstract class BaseActivity extends MBasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getStatusBarColor() != 0)
            TintStatusBar.setStatusBarColor(mContext, getStatusBarColor());
    }
}
