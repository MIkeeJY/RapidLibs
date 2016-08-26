package com.marno.mbasiclib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
/**
 * Created by 李刚 on 2016/5/13/15:09.
 */
public abstract class FragmentStatePagerAdapter2 extends FragmentStatePagerAdapter {
    protected int currentPosition = -1;
    protected Fragment currentFragment;

    public FragmentStatePagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        this.currentPosition = position;
        if (object instanceof Fragment) {
            this.currentFragment = (Fragment) object;
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
