package com.marno.mbasiclib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.List;
/**
 * Created by marno on 2016/5/13/15:09.
 */
public abstract class RecyclerAdapter<T> extends BaseRecyclerAdapter<T, RecyclerAdapterHelper> {

    public RecyclerAdapter(Context context, @NonNull int... layoutResIds) {
        super(context, layoutResIds);
    }

    public RecyclerAdapter(Context context, @Nullable List<T> data, @NonNull int... layoutResIds) {
        super(context, data, layoutResIds);
    }

    @Override
    protected RecyclerAdapterHelper getAdapterHelper(ViewHolder viewHolder) {
        return RecyclerAdapterHelper.get(viewHolder);
    }
}
