package com.yyc.yycframe.ui;

import android.view.View;

import com.yyc.yycframe.R;
import com.yyc.yycframe.R2;
import com.yyc.yycframe.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setStatusBarDark(true);
//        mTitleBar.setTitleText("MainActivity");
//        mTitleBar.setTitleBarBackgroundRes(R.color.colorPrimary);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, new MainFragment()).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useDefaultTitleBar() {
        return false;
    }

    //注解里边儿使用R2
    @OnClick({R2.id.tv_main})
    void handleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main:
                setStatusBarColor(R.color.colorPrimary);
                break;
        }
    }

}
