package com.marno.mbasiclib.basic.fragment;


import com.marno.mbasiclib.widgets.mrecyclerview.MRecyclerView;

/**
 * Created by 李刚 on 2016/3/14/13:53.
 * 支持下拉刷新，上拉加载更多的Fragment基类
 */
public abstract class MBasicRefreshFragment extends MBasicFragment
        implements MRecyclerView.LoadingListener {
    protected boolean mIsRefresh;

    @Override
    public void onRefresh() {
        mIsRefresh = true;
    }
}
