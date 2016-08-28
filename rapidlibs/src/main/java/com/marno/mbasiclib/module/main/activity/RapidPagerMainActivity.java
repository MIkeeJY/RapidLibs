package com.marno.mbasiclib.module.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.marno.mbasiclib.R;
import com.marno.mbasiclib.adapter.FragmentStatePagerAdapter2;
import com.marno.mbasiclib.basic.activity.MBasicActivity;
import com.marno.mbasiclib.data.entity.TabEntity;
import com.marno.mbasiclib.module.main.view.IMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marno on 2016/8/23/15:20.
 * 快速创建主页布局
 */
public abstract class RapidPagerMainActivity extends MBasicActivity implements IMainView{


    public static CommonTabLayout mTabLayout;
    protected ViewPager mViewPager;


    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.rapid_viewpager_activity_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mTabLayout = findView(R.id.tablayout);
        mViewPager = findView(R.id.pager);

        String[] tabNameArray = getTabNameArray();
        int[] tabSelectedIcon = getTabSelectedIcon();
        int[] tabUnselectedIcon = getTabUnselectedIcon();
        final List<Fragment> fragments = initFragments();

        setTab();
        if (tabNameArray == null) {
            mTabLayout.setTextsize(0);
            mTabLayout.setIconHeight(26);
            mTabLayout.setIconWidth(26);
        }

        for (int i = 0, size = fragments.size(); i < size; i++) {
            String title = tabNameArray == null ? "" : tabNameArray[i];
            int selectedIcon = tabSelectedIcon[i];
            int unSelectedIcon = tabUnselectedIcon[i];
            mTabEntities.add(new TabEntity(title, selectedIcon, unSelectedIcon));
        }

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentStatePagerAdapter2(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        scrollTogether();
    }

    private void scrollTogether() {
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
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
                mTabLayout.setCurrentTab(position);
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
