package com.yyc.yycframe.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.yyc.yycframe.entity.AppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerProvideModule {

    @Singleton
    @Provides
    public AppDataBase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "database-name").build();
    }
}
