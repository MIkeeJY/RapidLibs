package com.utouu.test.module.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.marno.mbasiclib.base.BaseFragment;
import com.marno.mbasiclib.utils.ToastUtil;
import com.utouu.test.R;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment {


    public static SecondFragment newIns() {
        return new SecondFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ToastUtil.common("正常的Toast");
                break;
            case R.id.btn2:
                ToastUtil.remind("提醒的Toast");
                break;
            case R.id.btn3:
                ToastUtil.error("错误的Toast");
                break;
            case R.id.btn4:
                ToastUtil.success("成功的Toast");
                break;
            case R.id.btn5:
                ToastUtil.warn("警告的Toast");
                break;
            case R.id.btn6:
                ToastUtil.systemToast("系统的tosat");
                break;
        }
    }
}
