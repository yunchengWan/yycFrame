package com.yyc.yycframe.ui;

import com.yyc.yycframe.R;
import com.yyc.yycframe.base.BaseSimpleFragment;

public class MainFragment extends BaseSimpleFragment {

    @Override
    protected int layout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTitleBar.setTitleText("Main Fragment");
        mTitleBar.setTitleBarBackgroundRes(R.color.colorPrimary);
    }

    @Override
    protected boolean useDefaultTitleBar() {
        return true;
    }
}
