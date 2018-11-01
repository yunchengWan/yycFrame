package com.yyc.yycframe.application;

import com.yyc.yycframe.di.DaggerAppComponent;
import com.yyc.yycframe.utils.ScreenAdapter;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class Application extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ScreenAdapter.setup(this);
    }
}
