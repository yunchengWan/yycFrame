package com.yyc.yycframe;

import com.yyc.yycframe.base.BaseView;
import com.yyc.yycframe.entity.User;

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter {
        void insertUser(User user);

        void queryUser();
    }
}
