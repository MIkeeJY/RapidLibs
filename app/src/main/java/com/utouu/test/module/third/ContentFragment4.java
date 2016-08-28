package com.utouu.test.module.third;


import android.os.Bundle;
import android.view.View;

import com.marno.mbasiclib.basic.fragment.MBasicFragment;
import com.utouu.test.R;


/**
 * Created by marno on 2016/8/23/15:20.
 */
public class ContentFragment4 extends MBasicFragment {

    public static ContentFragment4 newIns() {
        return new ContentFragment4();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_content4;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

}
