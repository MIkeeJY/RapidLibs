package com.utouu.test.module.third;


import android.os.Bundle;
import android.view.View;

import com.marno.mbasiclib.basic.fragment.MBasicFragment;
import com.utouu.test.R;


/**
 * Created by marno on 2016/8/23/15:20.
 */
public class ContentFragment extends MBasicFragment {

    public static ContentFragment newIns() {
        return new ContentFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

}
