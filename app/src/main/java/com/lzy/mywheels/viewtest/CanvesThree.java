package com.lzy.mywheels.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by bullet on 2018/1/3.
 */

public class CanvesThree  extends View {
    private static final String TAG = "CanvesThree";


    public Picture mPicture = new Picture();

    private int widthsize;
    private int heightsize;

    private int widthmade;

    private int heightmade;



    //创建一个画笔
    Paint paint = new Paint();



    public CanvesThree(Context context) {
        super(context);
//        recording();
    }

    public CanvesThree(Context context,  AttributeSet attrs) {
        super(context, attrs);
//        recording();
     paint.setColor(Color.BLUE);
     paint.setStrokeWidth(1);
     paint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         widthsize = MeasureSpec.getSize(widthMeasureSpec);
         widthmade = MeasureSpec.getMode(widthMeasureSpec);
         heightsize = MeasureSpec.getSize(heightMeasureSpec);
         heightmade = MeasureSpec.getMode(heightMeasureSpec);


        Log.d(TAG, "onMeasure:widthsize "+widthsize);
        Log.d(TAG, "onMeasure:widthmade "+widthmade);
        Log.d(TAG, "onMeasure:heightsize "+heightsize);
        Log.d(TAG, "onMeasure:heightmade "+heightmade);
    }


    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.translate(widthsize/2,heightsize/2);
        drawPolygon(canvas);



//
//        Path path = new Path();
//
//        path.lineTo(200,200);
//        path.setLastPoint(200,100);
//        path.lineTo(200,0);
//        canvas.drawPath(path,paint);




//        canvas.drawCircle(0,0,400,paint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。

//        canvas.drawColor(Color.BLUE); //绘制蓝色

//        canvas.drawPicture(mPicture);


//        canvas.drawPicture(mPicture);

//        mPicture.draw(canvas);
    }




    private void drawPolygon(Canvas canvas){

        int count =6;

        int centerX = widthsize/2;
        int centerY = heightsize/2;
        float radius = Math.min(heightsize, widthsize)/2*0.9f;
         float angle = (float) (Math.PI*2/count);
        Path path = new Path();
        float r = radius/(count-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<count;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();
            for(int j=0;j<count;j++){
                if(j==0){
                    path.moveTo(centerX+curR,centerY);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX+curR*Math.cos(angle*j));
                    float y = (float) (centerY+curR*Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, paint);
        }
    }



    private void recording(){
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        Log.d(TAG, "recording: "+"进来了");
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        paint.setStrokeWidth(10);
        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,400,paint);


        mPicture.endRecording();
        Log.d(TAG, "recording: "+"出来了");
    }


}
