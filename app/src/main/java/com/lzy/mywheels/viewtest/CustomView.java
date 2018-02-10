package com.lzy.mywheels.viewtest;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by bullet on 2018/1/9.
 */

public class CustomView extends View {

    protected Context mCurrentContext;
    protected int mViewWidth;

    protected int mViewHeigh;

    protected Paint mDefaultPaint = new Paint();



    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mCurrentContext = context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewHeigh=w;
        mViewHeigh = h;

    }


}
