package com.utouu.test.module.third;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.marno.mbasiclib.module.main.activity.RapidMainActivity;
import com.utouu.test.R;

import java.util.ArrayList;

public class ThirdActivity extends RapidMainActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    protected void initData() {

    }


    @Override
    public String[] getTabNameArray() {
        return new String[]{"新闻", "视频", "图片", "我的"};
//        return null;
    }

    @NonNull
    @Override
    public int[] getTabUnselectedIcon() {
        return new int[]{R.drawable.ic_news, R.drawable.ic_video,
                R.drawable.ic_image, R.drawable.ic_me};
    }

    @NonNull
    @Override
    public int[] getTabSelectedIcon() {
        return new int[]{
                R.drawable.ic_news_selected, R.drawable.ic_video_selected,
                R.drawable.ic_image_selected, R.drawable.ic_me_selected};
    }

    @NonNull
    @Override
    public ArrayList<Fragment> initFragments() {
        mFragments.add(ContentFragment.newIns());
        mFragments.add(ContentFragment2.newIns());
        mFragments.add(ContentFragment3.newIns());
        mFragments.add(ContentFragment4.newIns());
        return mFragments;
    }

    @Override
    public void setTab() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
