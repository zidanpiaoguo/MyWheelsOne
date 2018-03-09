package com.tianhui.test.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tianhui.test.R;

/**
 * Created by bullet on 2018/3/1.
 */

public class TouchView extends CustomView {

    Bitmap mBitmap;
    RectF mBitmapRectF;
    Matrix mBitmapMatrix;

    boolean canDrag = false;

    PointF lastPoint = new PointF(0,0);


    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth=960/2;
        options.outHeight = 800/2;

        mBitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher, options);
        mBitmapRectF = new RectF(0,0,mBitmap.getWidth(), mBitmap.getHeight());
        mBitmapMatrix = new Matrix();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getMetaState()){
            case MotionEvent.ACTION_DOWN:
                if(mBitmapRectF.contains(event.getX(),event.getY())){
                    canDrag = true;
                    lastPoint.set(event.getX(),event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                canDrag=false;
            case MotionEvent.ACTION_MOVE:
                if(canDrag){
                    // 移动图片
                    mBitmapMatrix.postTranslate(event.getX() - lastPoint.x, event.getY() - lastPoint.y);
                    // 更新上一次点位置
                    lastPoint.set(event.getX(), event.getY());

                    // 更新图片区域
                    mBitmapRectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                    mBitmapMatrix.mapRect(mBitmapRectF);

                    invalidate();

                }

        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mBitmapMatrix, mDefaultPaint);
    }
}
