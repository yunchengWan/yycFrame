package com.yyc.yycframe.di;

import com.yyc.yycframe.application.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationBindingModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        PresenterBindingModule.class,
        ManagerProvideModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(android.app.Application application);

        AppComponent build();
    }
}
