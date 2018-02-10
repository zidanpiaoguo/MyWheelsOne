package com.lzy.mywheels.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lzy.mywheels.App.MyToast;

/**
 * Created by bullet on 2018/1/9.
 */

public class RegionClickView extends CustomView {

    Region circleRegion ;
    Path circlePath;


    public RegionClickView(Context context) {
        super(context);
        mDefaultPaint.setColor(0xFF4E5268);
        circleRegion = new Region();
        circlePath = new Path();

    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDefaultPaint.setColor(0xFF4E5268);
        circleRegion = new Region();
        circlePath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        circlePath.addCircle(w/2,h/2,300,Path.Direction.CW);

        Region globalRegion = new Region(-w, -h, w, h);

        // ▼将 Path 添加到 Region 中
        circleRegion.setPath(circlePath, globalRegion);





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                if(circleRegion.contains(x,y)){
                    MyToast.makeText("我被点击了");

                }
                break;
        }

        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // ▼注意此处将全局变量转化为局部变量，方便 GC 回收 canvas
        Path circle = circlePath;
        // 绘制圆
        canvas.drawPath(circle,mDefaultPaint);

    }
}
