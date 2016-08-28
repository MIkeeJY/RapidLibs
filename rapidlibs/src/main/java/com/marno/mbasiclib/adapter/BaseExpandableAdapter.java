package com.marno.mbasiclib.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by marno on 2016/5/13/15:09.
 */
abstract class BaseExpandableAdapter<T, V, H extends ExpandableAdapterHelper> extends BaseExpandableListAdapter implements DataIO<T> {

    protected ArrayList<T> data;
    protected Context context;
    protected final int groupLayoutResId;
    protected final int childLayoutResId;

    public BaseExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId) {
        this(context, groupLayoutResId, childLayoutResId, null);
    }

    public BaseExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.context = context;
        this.groupLayoutResId = groupLayoutResId;
        this.childLayoutResId = childLayoutResId;
    }

    @Override
    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        data.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elements) {
        data.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        data.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<T> elements) {
        data.removeAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (data != null && data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
        replaceAt(data.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public T get(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public ArrayList<T> getAll() {
        return data;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean contains(T elem) {
        return data.contains(elem);
    }

    @Override
    public int getGroupCount() {
        return getSize();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition < getGroupCount()) {
            List<V> children = getChildren(groupPosition);
            if (children != null) {
                return children.size();
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public T getGroup(int groupPosition) {
        return get(groupPosition);
    }

    @Override
    public V getChild(int groupPosition, int childPosition) {
        if (groupPosition < getGroupCount() && childPosition < getChildrenCount(groupPosition)) {
            return getChildren(groupPosition).get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (groupPosition < getGroupCount()) {
            return childPosition;
        }
        return -1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        H help = getAdapterHelper(groupPosition, -1, convertView, parent);
        T groupItem = getGroup(groupPosition);
        convertGroupView(isExpanded, help, groupItem);
        return help.getItemView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        H help = getAdapterHelper(groupPosition, childPosition, convertView, parent);
        V childItem = getChild(groupPosition, childPosition);
        convertChildView(isLastChild, help, childItem);
        return help.getItemView();
    }

    @Override
    public void onEmptyData() {
    }

    @Override
    public void onHasData() {
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (getSize() == 0) {
            onEmptyData();
        } else {
            onHasData();
        }
    }

    protected abstract List<V> getChildren(int groupPosition);

    protected abstract void convertGroupView(boolean isExpanded, H helper, T item);

    protected abstract void convertChildView(boolean isLastChild, H helper, V item);

    protected abstract H getAdapterHelper(int groupPosition, int childPosition, View convertView, ViewGroup parent);
}
