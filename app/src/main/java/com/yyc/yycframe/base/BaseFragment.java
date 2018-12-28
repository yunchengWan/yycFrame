package com.yyc.yycframe.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * 包含Dagger框架的Fragment基类
 * 需要在{@link com.yyc.yycframe.di.FragmentBindingModule}里面声明Activity
 * 需要在{@link com.yyc.yycframe.di.PresenterBindingModule} 里面绑定Presenter
 */
public abstract class BaseFragment<T extends IPresenter> extends DaggerFragment {

    @Inject
    @Presenter
    protected T mPresenter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(layout(), container);
        mUnbinder = ButterKnife.bind(this, rootView);
        PresenterManager.injectFragment(this);
        initData();
        initView();
        return rootView;
    }

    @LayoutRes
    protected abstract int layout();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
