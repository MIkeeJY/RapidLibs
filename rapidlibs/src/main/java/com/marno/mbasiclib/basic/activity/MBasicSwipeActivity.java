package com.marno.mbasiclib.basic.activity;

import android.os.Bundle;

import com.jude.swipbackhelper.SwipeBackHelper;

/**
 * Created by 李刚 on 2016/8/25/21:48.
 * 支持滑动返回不支持列表刷新
 */
public abstract class MBasicSwipeActivity extends MBasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)//get current instance
                .setSwipeBackEnable(true)//on-off
                .setSwipeEdge(50)//set the touch area。200 mean only the left 200px of screen can touch to begin swipe.
                .setSwipeEdgePercent(0.15f)//0.2 mean left 20% of screen can touch to begin swipe.
                .setSwipeSensitivity(0.2f)//sensitiveness of the gesture。0:slow  1:sensitive
                .setSwipeRelateEnable(true)//if should move together with the following Activity
                .setSwipeRelateOffset(500);//the Offset of following Activity when setSwipeRelateEnable(true)
//                .setDisallowInterceptTouchEvent(true)//your view can hand the events first.default false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

}
