package com.yyc.yycframe;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.yyc.yycframe.base.Presenter;
import com.yyc.yycframe.base.PresenterManager;
import com.yyc.yycframe.utils.ScreenAdapter;
import com.yyc.yycframe.utils.StatusBarUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    @Inject
    @Presenter
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ScreenAdapter.excludeActivity(this);

        setContentView(R.layout.activity_main);

        StatusBarUtils.setStatusBarLight(this, true);

        PresenterManager.injectActivity(this);


        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("111");
        publishSubject.onNext("222");
        Log.d("MainActivity", Thread.currentThread().getName() + "");

        publishSubject.onNext("333");

    }

}
