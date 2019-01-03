package com.yyc.yycframe.weight;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;

public interface ITitleBar {

    void setTitleBarBackground(@ColorInt int color);

    void setTitleBarBackgroundRes(@ColorRes int color);

    void setLeftImageVisible(int visible);

    void setLeftImageRes(@DrawableRes int imageRes);

    void setLeftImageClickListener(View.OnClickListener listener);

    void setRightImageVisible(int visible);

    void setRightImageRes(@DrawableRes int imageRes);

    void setRightImageClickListener(View.OnClickListener listener);

    void setTitleText(String titleText);

    void setTitleTextRes(@StringRes int titleTextRes);

    void setTitleTextSize(int titleTextSize);

    void setTitleTextColor(@ColorInt int titleTextColor);

    void setTitleTextColorRes(@ColorRes int titleTextColorRes);

    void setUnderlineVisible(int visible);

    void addCustomView(View view, ViewGroup.LayoutParams layoutParams);
}
