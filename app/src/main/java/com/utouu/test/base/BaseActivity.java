package com.utouu.test.base;

import android.os.Bundle;

import com.marno.mbasiclib.widgets.TintStatusBar;

/**
 * Created by Marno on 2016/8/25/15:15.
 */
public abstract class BaseActivity extends com.marno.mbasiclib.base.BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getStatusBarColor() != 0)
            TintStatusBar.setStatusBarColor(mContext, getResources().getColor(getStatusBarColor()));
    }
}
