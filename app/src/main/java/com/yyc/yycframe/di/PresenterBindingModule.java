package com.yyc.yycframe.di;

import com.yyc.yycframe.MainContract;
import com.yyc.yycframe.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PresenterBindingModule {

    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}
