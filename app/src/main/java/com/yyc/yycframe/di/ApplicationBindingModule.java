package com.yyc.yycframe.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationBindingModule {
    @Binds
    abstract Context bindApplicationContext(Application application);
}
