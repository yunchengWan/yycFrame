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
    }

    @Override
    protected void initData() {

    }

    //注解里边儿使用R2
    @OnClick({R2.id.tv_main})
    void handleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main:
                setStatusBarColor("#00FFFF");
                break;
        }
    }

}
