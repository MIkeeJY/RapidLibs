package com.marno.mbasiclib.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
/**
 * Created by marno on 2016/5/13/15:09.
 */
public abstract class ExpandableAdapter<T, V> extends BaseExpandableAdapter<T, V, ExpandableAdapterHelper> {

    public ExpandableAdapter(Context context, @LayoutRes int groupLayoutResId, @LayoutRes int childLayoutResId) {
        super(context, groupLayoutResId, childLayoutResId);
    }

    public ExpandableAdapter(Context context, @LayoutRes int groupLayoutResId, @LayoutRes int childLayoutResId, @Nullable List<T> data) {
        super(context, groupLayoutResId, childLayoutResId, data);
    }

    @Override
    protected ExpandableAdapterHelper getAdapterHelper(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
        if (childPosition == -1) {
            return ExpandableAdapterHelper.get(context, convertView, parent, groupLayoutResId, groupPosition, childPosition);
        }
        return ExpandableAdapterHelper.get(context, convertView, parent, childLayoutResId, groupPosition, childPosition);
    }
}
