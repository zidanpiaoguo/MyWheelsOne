package com.lzy.mywheels.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zidan on 2017/10/11.
 */

public class BulletView extends View{

    private Paint mPaint  =new Paint();

    public BulletView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawPoint(200,400,mPaint);
        canvas.drawPoints(new float[]{500,30,500,60,500,90},mPaint);

    }

    private void initPaint(){
        mPaint.setColor(Color.BLACK);   //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL); //设置画笔模式为填充

        //      STROKE                //描边
        //      FILL                  //填充
        //      FILL_AND_STROKE       //描边加填充

        mPaint.setStrokeWidth(10f);
    }



}
