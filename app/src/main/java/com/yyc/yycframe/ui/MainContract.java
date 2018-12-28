package com.yyc.yycframe.ui;

import com.yyc.yycframe.base.BaseView;
import com.yyc.yycframe.base.IPresenter;
import com.yyc.yycframe.entity.User;

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter extends IPresenter {
        void insertUser(User user);

        void queryUser();
    }
}
