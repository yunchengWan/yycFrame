package com.yyc.yycframe.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yyc.yycframe.annotation.Presenter;
import com.yyc.yycframe.weight.ITitleBar;
import com.yyc.yycframe.weight.TitleBarView;

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

    private final static int DEFAULT_TITLE_HEIGHT = 48; //dp

    @Inject
    @Presenter
    protected T mPresenter;
    private Unbinder mUnbinder;

    protected ITitleBar mTitleBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;
        if (useDefaultTitleBar()) {
            rootView = initRootViewWithTitleBar(inflater);
        } else {
            rootView = inflater.inflate(layout(), container);
        }
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

    protected boolean useDefaultTitleBar() {
        return false;
    }

    @NonNull
    private View initRootViewWithTitleBar(LayoutInflater inflater) {
        FrameLayout.LayoutParams rootViewPara = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout rootView = new LinearLayout(getContext());
        rootView.setLayoutParams(rootViewPara);

        rootView.setOrientation(LinearLayout.VERTICAL);
        mTitleBar = new TitleBarView(getContext());
        LinearLayout.LayoutParams titleBarPara = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleBarPara.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TITLE_HEIGHT, getResources().getDisplayMetrics());
        rootView.addView((View) mTitleBar, titleBarPara);

        inflater.inflate(layout(), rootView, true);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
