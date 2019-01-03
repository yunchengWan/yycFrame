package com.yyc.yycframe.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yyc.yycframe.R;

/**
 * 简单的TitleBar
 */
public class TitleBarView extends RelativeLayout implements ITitleBar {
    private final static int DEFAULT_TITLE_TEXT_SIZE = 20;
    private final static int DEFAULT_TITLE_TEXT_COLOR = Color.BLACK;
    private final static int DEFAULT_TITLE_TEXT_GRAVITY = 2; //(1--left, 2--center, 3--right)

    private final static int DEFAULT_ICON_WIDTH = 25;
    private final static int DEFAULT_ICON_HEIGHT = 25;
    private final static int DEFAULT_MARGIN = 12;
    private final static int DEFAULT_PADDING = 5;

    private final static float DEFAULT_UNDERLINE_HEIGHT = 0.5f;

    private final static int DEFAULT_UNDERLINE_COLOR = 0xFF999999; //argb

    private TextView mTitleTv;
    private ImageView mLeftIv;
    private ImageView mRightIv;
    private View mUnderlineView;

    private int mTitleTextSize;
    private int mTitleTextColor;
    private String mTitleText;
    private int mTitleTextGravity;

    private int mLeftIconWidth;
    private int mLeftIconHeight;
    private int mLeftIconMargin;
    private int mLeftIconPadding;
    private Drawable mLeftDrawable;

    private int mRightIconWidth;
    private int mRightIconHeight;
    private Drawable mRightDrawable;
    private int mRightIconMargin;
    private int mRightIconPadding;

    private int mUnderlineColor;

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Title TextView Attr Obtain
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView);
        mTitleTextSize = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_titleTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TITLE_TEXT_SIZE, getResources().getDisplayMetrics())
        );
        mTitleTextColor = ta.getColor(R.styleable.TitleBarView_titleTextColor, DEFAULT_TITLE_TEXT_COLOR);
        mTitleText = ta.getString(R.styleable.TitleBarView_titleText);
        mTitleTextGravity = ta.getInt(R.styleable.TitleBarView_titleTextGravity, DEFAULT_TITLE_TEXT_GRAVITY);

        //Left Icon Attr Obtain
        mLeftIconWidth = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_leftIconWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_WIDTH, getResources().getDisplayMetrics())
        );
        mLeftIconHeight = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_leftIconHeight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_HEIGHT, getResources().getDisplayMetrics())
        );
        mLeftIconMargin = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_leftIconMargin,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_MARGIN, getResources().getDisplayMetrics())
        );
        mLeftIconPadding = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_leftIconPadding,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PADDING, getResources().getDisplayMetrics())
        );
        mLeftDrawable = ta.getDrawable(R.styleable.TitleBarView_leftIconRes);

        //Right Icon Attr Obtain
        mRightIconWidth = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_rightIconWidth,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_WIDTH, getResources().getDisplayMetrics())
        );
        mRightIconHeight = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_rightIconHeight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_ICON_HEIGHT, getResources().getDisplayMetrics())
        );
        mRightIconMargin = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_rightIconMargin,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_MARGIN, getResources().getDisplayMetrics())
        );
        mRightIconPadding = ta.getDimensionPixelSize(
                R.styleable.TitleBarView_rightIconPadding,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PADDING, getResources().getDisplayMetrics())
        );
        mRightDrawable = ta.getDrawable(R.styleable.TitleBarView_rightIconRes);

        mUnderlineColor = ta.getColor(R.styleable.TitleBarView_underlineColor, DEFAULT_UNDERLINE_COLOR);
        ta.recycle();

        initView();
    }

    private void initView() {

        //init Left Icon
        mLeftIv = new ImageView(getContext());
        mLeftIv.setId(View.generateViewId());
        if (mLeftDrawable != null) {
            mLeftIv.setImageDrawable(mLeftDrawable);
        }
        mLeftIv.setPadding(mLeftIconPadding, mLeftIconPadding, mLeftIconPadding, mLeftIconPadding);
        RelativeLayout.LayoutParams leftIvPara = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftIvPara.width = mLeftIconWidth;
        leftIvPara.height = mLeftIconHeight;
        leftIvPara.leftMargin = mLeftIconMargin;
        leftIvPara.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        leftIvPara.addRule(ALIGN_PARENT_START, RelativeLayout.TRUE);
        addView(mLeftIv, leftIvPara);

        //init Title Text
        mTitleTv = new TextView(getContext());
        mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        mTitleTv.setTextColor(mTitleTextColor);
        if (!TextUtils.isEmpty(mTitleText)) {
            mTitleTv.setText(mTitleText);
        }
        mTitleTv.setId(View.generateViewId());
        RelativeLayout.LayoutParams titleTvPara = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (mTitleTextGravity == 2) {
            titleTvPara.addRule(CENTER_IN_PARENT, RelativeLayout.TRUE);
        }
        if (mTitleTextGravity == 1) {
            titleTvPara.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
            titleTvPara.addRule(END_OF, mLeftIv.getId());
            titleTvPara.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_MARGIN, getResources().getDisplayMetrics());
        }
        addView(mTitleTv, titleTvPara);

        //init Right Icon
        mRightIv = new ImageView(getContext());
        mRightIv.setId(View.generateViewId());
        if (mRightDrawable != null) {
            mRightIv.setImageDrawable(mRightDrawable);
        }
        mRightIv.setPadding(mRightIconPadding, mRightIconPadding, mRightIconPadding, mRightIconPadding);
        RelativeLayout.LayoutParams rightIvPara = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightIvPara.width = mRightIconWidth;
        rightIvPara.height = mRightIconHeight;
        rightIvPara.rightMargin = mRightIconMargin;
        rightIvPara.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        rightIvPara.addRule(ALIGN_PARENT_END, RelativeLayout.TRUE);
        addView(mRightIv, rightIvPara);

        mUnderlineView = new View(getContext());
        mUnderlineView.setBackgroundColor(mUnderlineColor);
        mUnderlineView.setId(View.generateViewId());
        RelativeLayout.LayoutParams underlinePara = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        underlinePara.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_UNDERLINE_HEIGHT, getResources().getDisplayMetrics());
        underlinePara.addRule(ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        addView(mUnderlineView, underlinePara);
    }

    @Override
    public void setTitleBarBackground(@ColorInt int color) {
        setBackgroundColor(color);
    }

    @Override
    public void setTitleBarBackgroundRes(@ColorRes int color) {
        setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    @Override
    public void setLeftImageVisible(int visible) {
        if (mLeftIv != null) {
            mLeftIv.setVisibility(visible);
        }
    }

    @Override
    public void setLeftImageRes(@DrawableRes int imageRes) {
        if (mLeftIv != null) {
            mLeftIv.setImageResource(imageRes);
        }
    }

    @Override
    public void setLeftImageClickListener(OnClickListener listener) {
        if (mLeftIv != null) {
            mLeftIv.setOnClickListener(listener);
        }
    }

    @Override
    public void setRightImageVisible(int visible) {
        if (mRightIv != null) {
            mRightIv.setVisibility(visible);
        }
    }

    @Override
    public void setRightImageRes(@DrawableRes int imageRes) {
        if (mRightIv != null) {
            mRightIv.setImageResource(imageRes);
        }
    }

    @Override
    public void setRightImageClickListener(OnClickListener listener) {
        if (mRightIv != null) {
            mRightIv.setOnClickListener(listener);
        }
    }

    @Override
    public void setTitleText(String titleText) {
        if (mTitleTv != null) {
            mTitleTv.setText(titleText);
        }
    }

    @Override
    public void setTitleTextRes(@StringRes int titleTextRes) {
        if (mTitleTv != null) {
            mTitleTv.setText(titleTextRes);
        }
    }

    @Override
    public void setTitleTextSize(int titleTextSize) {
        if (mTitleTv != null) {
            mTitleTv.setTextSize(titleTextSize);
        }
    }

    @Override
    public void setTitleTextColor(@ColorInt int titleTextColor) {
        if (mTitleTv != null) {
            mTitleTv.setTextColor(titleTextColor);
        }
    }

    @Override
    public void setTitleTextColorRes(@ColorRes int titleTextColorRes) {
        if (mTitleTv != null) {
            mTitleTv.setTextColor(ContextCompat.getColor(getContext(), titleTextColorRes));
        }
    }

    @Override
    public void setUnderlineVisible(int visible) {
        if (mUnderlineView != null) {
            mUnderlineView.setVisibility(visible);
        }
    }

    @Override
    public void addCustomView(View view, ViewGroup.LayoutParams layoutParams) {
        addView(view, layoutParams);
    }
}
