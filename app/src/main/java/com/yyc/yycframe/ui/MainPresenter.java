package com.yyc.yycframe.ui;

import android.util.Log;

import com.yyc.yycframe.base.BasePresenter;
import com.yyc.yycframe.entity.AppDataBase;
import com.yyc.yycframe.entity.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    AppDataBase mAppDb;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void insertUser(User user) {
        if (user != null) {
            new Thread(() -> mAppDb.userDao().insertUser(user)).start();
        }
    }


    @Override
    public void queryUser() {
        Disposable disposable = mAppDb.userDao().getAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            Log.d("MainPresenter", "" + users);
                        },
                        throwable -> {
                            Log.d("MainPresenter", "Error");
                        }
                );

        addDisposable(disposable);
    }
}
