package com.yyc.yycframe.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.support.v4.app.Fragment;

import java.lang.reflect.Field;

public class PresenterManager {

    @SuppressWarnings("unchecked")
    public static void injectView(BaseView view) {
        Field[] fields = view.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getAnnotation(Presenter.class) != null) {
                try {
                    f.setAccessible(true);
                    Object presenter = f.get(view);
                    if (presenter instanceof BasePresenter) {
                        BasePresenter p = (BasePresenter) presenter;
                        p.takeView(view);
                        if (view instanceof LifecycleOwner) {
                            ((LifecycleOwner) view).getLifecycle().addObserver(p);
                        } else {
                            throw new IllegalArgumentException("view must extends Activity or Fragment");
                        }
                    } else {
                        throw new IllegalArgumentException("presenter must extends BasePresenter");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void injectActivity(Activity activity) {
        if (activity instanceof BaseView) {
            injectView((BaseView) activity);
        } else {
            throw new IllegalArgumentException("activity must implements BaseView");
        }
    }

    public static void injectFragment(Fragment fragment) {
        if (fragment instanceof BaseView) {
            injectView((BaseView) fragment);
        } else {
            throw new IllegalArgumentException("fragment must implements BaseView");
        }
    }
}
