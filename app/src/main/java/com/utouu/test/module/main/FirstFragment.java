package com.utouu.test.module.main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.marno.mbasiclib.adapter.RecyclerAdapter;
import com.marno.mbasiclib.adapter.RecyclerAdapterHelper;
import com.marno.mbasiclib.basic.fragment.MBasicRefreshFragment;
import com.marno.mbasiclib.manager.BannerManager;
import com.marno.mbasiclib.manager.GlideManager;
import com.marno.mbasiclib.widgets.MultipleStatusView;
import com.marno.mbasiclib.widgets.xrecyclerview.XRecyclerView;
import com.marno.mbasiclib.widgets.xrecyclerview.ProgressStyle;
import com.utouu.test.R;
import com.utouu.test.data.entity.TestEntity;
import com.utouu.test.module.second.SecondActivity;
import com.utouu.test.module.third.ThirdActivity;
import com.utouu.test.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by marno on 2016/8/23/15:20.
 */
public class FirstFragment extends MBasicRefreshFragment {

    @BindView(R.id.content_view)
    XRecyclerView mRecyclerView;
    @BindView(R.id.msvLayout)
    MultipleStatusView mMsvLayout;
    private RecyclerAdapter<TestEntity> mAdapter;
    private ConvenientBanner mBanner;

    public static FirstFragment newIns() {
        return new FirstFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startTurning(3000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.stopTurning();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_first;
    }


    @Override
    protected void initData() {
        ArrayList<TestEntity> entities = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entities.add(new TestEntity(i + "测试数据", R.drawable.avater));
        }

        Observable.just(entities)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<TestEntity>>() {
                    @Override
                    public void call(ArrayList<TestEntity> entityArrayList) {
                        mMsvLayout.content();
                        if (mIsRefresh) mAdapter.clear();
                        mAdapter.addAll(entityArrayList);
                        mIsRefresh = false;
                    }
                });
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mMsvLayout.loading();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingListener(this);

        setBanner();
        setAdapter();
    }

    private void setAdapter() {
        mAdapter = new RecyclerAdapter<TestEntity>(mContext, R.layout.item_textview) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, TestEntity item) {
                helper.setText(R.id.tv_name, item.name);
                ImageView ivAvater = (ImageView) helper.getItemView().findViewById(R.id.iv_shape);
                int position = helper.getLayoutPosition();
                if (position % 2 == 0) {
                    GlideManager.loadRoundImg(item.img, ivAvater);
                } else {
                    GlideManager.loadImg(item.img, ivAvater);
                }
                helper.getItemView().setOnClickListener(v ->
                        ActivityUtil.to(mContext, SecondActivity.class));
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    //设置banner
    private void setBanner() {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, null);
        mBanner = (ConvenientBanner) bannerView.findViewById(R.id.banner);

        mBanner.setPageIndicator(new int[]{
                R.drawable.shape_indicator, R.drawable.shape_indicator_selected})
                .setPageIndicatorAlign(
                        ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        List<Integer> images = Arrays.asList(R.drawable.ic_test_1, R.drawable.ic_test_2, R.drawable.ic_test_3);
        BannerManager.showBanner(mBanner, images);

        mRecyclerView.addHeaderView(bannerView);
        mBanner.setOnItemClickListener(position -> {
            ActivityUtil.to(mContext, ThirdActivity.class);
        });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        Observable.just("1")
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    initData();
                    mRecyclerView.refreshComplete();
                });
    }

    @Override
    public void onLoadMore() {
        Observable.just("1")
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ArrayList<TestEntity> entities = new ArrayList<>();
                    for (int i = 0; i < 30; i++) {
                        entities.add(new TestEntity("加载更多" + i + "测试数据", R.drawable.avater));
                    }
                    mAdapter.addAll(entities);
                    mRecyclerView.loadMoreComplete();
                });
    }

}
