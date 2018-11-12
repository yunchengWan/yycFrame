package com.yyc.yycframe.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * MVP BasePresenter
 * <p>
 * T类型为继承BaseView的View层（一般是activity和fragment）
 */
public abstract class BasePresenter<T extends BaseView> implements LifecycleObserver {

    /**
     * View 实例
     */
    protected T mView;

    /**
     * 用于保存请求disposable 便于最后取消请求
     */
    protected List<Disposable> mRequestQueue = new ArrayList<>();


    /**
     * 通过PresenterManager来设定view
     *
     * @param view view
     */
    protected void takeView(T view) {
        mView = view;
    }

    /**
     * 添加Disposable
     */
    protected void addDisposable(Disposable disposable) {
        mRequestQueue.add(disposable);
    }

    /**
     * 通过lifecycle来把mView置空
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void dropView() {
        mView = null;
    }

    /**
     * 通过lifecycle取消未完成的请求
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void cancelRequest() {
        for (Disposable d : mRequestQueue) {
            if (d != null && !d.isDisposed()) {
                d.dispose();
            }
        }
        mRequestQueue.clear();
    }

}
