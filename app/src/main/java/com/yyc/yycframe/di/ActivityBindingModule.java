package com.yyc.yycframe.di;

import com.yyc.yycframe.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
