package com.utouu.test.module.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.marno.mbasiclib.ui.RapidMainActivity;
import com.utouu.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marno on 2016/8/23/15:20.
 * 主页面
 */
public class MainActivity extends RapidMainActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();


    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected String[] getTabNameArray() {
        return new String[]{"新闻", "视频", "图片", "我的"};
//        return null;
    }

    @NonNull
    @Override
    protected int[] getTabUnselectedIcon() {
        return new int[]{R.drawable.ic_news, R.drawable.ic_video,
                R.drawable.ic_image, R.drawable.ic_me};
    }

    @NonNull
    @Override
    protected int[] getTabSelectedIcon() {
        return new int[]{
            R.drawable.ic_news_selected, R.drawable.ic_video_selected,
                    R.drawable.ic_image_selected, R.drawable.ic_me_selected};
    }

    @NonNull
    @Override
    protected List<Fragment> initFragments() {
        mFragments.add(FirstFragment.newIns());
        mFragments.add(SecondFragment.newIns());
        mFragments.add(ThirdFragment.newIns());
        mFragments.add(FirstFragment.newIns());
        return mFragments;
    }

    @Override
    protected void setTab() {

    }
}
