package com.utouu.test.module.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.marno.mbasiclib.adapter.FragmentStatePagerAdapter2;
import com.marno.mbasiclib.base.BaseActivity;
import com.utouu.test.R;
import com.utouu.test.data.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by marno on 2016/8/23/15:20.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tl_2)
    CommonTabLayout mTabLayout_2;
    @BindView(R.id.vp_2)
    ViewPager mViewPager;

    private String[] mTitles = {"新闻", "视频", "图片", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.ic_news, R.drawable.ic_video,
            R.drawable.ic_image, R.drawable.ic_me};
    private int[] mIconSelectIds = {
            R.drawable.ic_news_selected, R.drawable.ic_video_selected,
            R.drawable.ic_image_selected, R.drawable.ic_me_selected};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected int getStatusBarColor() {
        return 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mFragments.add(FirstFragment.newIns());
        mFragments.add(SecondFragment.newIns());
        mFragments.add(ThirdFragment.newIns());
        mFragments.add(FirstFragment.newIns());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentStatePagerAdapter2(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        });
        tl_2();
        mTabLayout_2.showDot(3);
        mTabLayout_2.showMsg(2, 2);
    }

    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }


    @Override
    public void onBackPressed() {
        quit();
    }
}
