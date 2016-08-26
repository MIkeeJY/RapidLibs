package com.utouu.test.module;

import android.os.Bundle;

import com.marno.mbasiclib.base.BaseSwipeActivity;
import com.utouu.test.R;

/**
 * Created by marno on 2016/8/23/15:20.
 */
public class SecondActivity extends BaseSwipeActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


}
