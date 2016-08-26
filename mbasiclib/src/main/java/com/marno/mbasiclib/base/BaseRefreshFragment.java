package com.marno.mbasiclib.base;


import com.marno.mbasiclib.widgets.mrecyclerview.MRecyclerView;

/**
 * Created by 李刚 on 2016/3/14/13:53.
 */
public abstract class BaseRefreshFragment extends BaseFragment
        implements MRecyclerView.LoadingListener {
    protected boolean mIsRefresh;

    @Override
    public void onRefresh() {
        mIsRefresh = true;
    }
}
