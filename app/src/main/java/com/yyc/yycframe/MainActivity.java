package com.yyc.yycframe;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.yyc.yycframe.base.Presenter;
import com.yyc.yycframe.base.PresenterManager;
import com.yyc.yycframe.entity.AppDataBase;
import com.yyc.yycframe.entity.User;
import com.yyc.yycframe.utils.ScreenAdapter;
import com.yyc.yycframe.utils.StatusBarUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    @Inject
    @Presenter
    MainContract.Presenter mPresenter;

    int aaa = 0x101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusBarLight(this, true);
        PresenterManager.injectActivity(this);

        findViewById(R.id.tv_main)
                .setOnClickListener(
                        v -> {
                            User user = new User();
                            aaa += 1;
                            user.setId(aaa);
                            user.setName("name" + aaa);
                            user.setAge(aaa / 30);
                            mPresenter.insertUser(user);
                        }
                );

        findViewById(R.id.view_main_bg)
                .setOnClickListener(
                        v -> {
                            mPresenter.queryUser();
                        }
                );
    }

}
