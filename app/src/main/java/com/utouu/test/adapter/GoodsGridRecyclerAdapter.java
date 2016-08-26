package com.utouu.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.marno.mbasiclib.adapter.RecyclerAdapter;
import com.marno.mbasiclib.adapter.RecyclerAdapterHelper;
import com.marno.mbasiclib.utils.ToastUtil;
import com.utouu.test.R;
import com.utouu.test.data.entity.GoodsEntity;

/**
 * Created by marno on 2016/6/26/15:47.
 * 商品的适配器-grid排列
 */
public class GoodsGridRecyclerAdapter extends RecyclerAdapter<GoodsEntity> {
    private Context mContext;

    public GoodsGridRecyclerAdapter(Context context) {
        this(context, R.layout.item_goods_grid);
    }

    public GoodsGridRecyclerAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
        this.mContext = context;
    }

    @Override
    protected void convert(RecyclerAdapterHelper helper, GoodsEntity item) {
        helper.setImageUrl(R.id.iv_goodsLogo_goodsFragment, item.goods_thumb)
                .setText(R.id.tv_goodsName_goodsFragment, item.goods_name);
        helper.setOnClickListener(R.id.iv_goodsLogo_goodsFragment, v -> {

        });

        helper.getItemView().setOnClickListener(v -> ToastUtil.common("1234"));
    }
}
