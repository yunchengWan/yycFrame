package com.yyc.yycframe;

import android.os.Bundle;
import android.util.Log;

import com.yyc.yycframe.base.Presenter;
import com.yyc.yycframe.base.PresenterManager;
import com.yyc.yycframe.utils.StatusBarUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    @Inject
    @Presenter
    MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusBarLight(this, true);
        PresenterManager.injectActivity(this);

        //Room Demo
        /*findViewById(R.id.tv_main)
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
                );*/

        Disposable d = Observable.create(
                emitter -> new Thread(() -> {
                    Log.d("MainActivity", "Thread: " + Thread.currentThread());
                    emitter.onNext("123");
                }).start()
        ).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(
                str -> Log.d("MainActivity", "Thread: " + Thread.currentThread()),
                Throwable::printStackTrace
        );
    }

}
