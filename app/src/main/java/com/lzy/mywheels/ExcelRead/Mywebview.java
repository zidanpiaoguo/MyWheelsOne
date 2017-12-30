package com.lzy.mywheels.ExcelRead;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by zidan on 2017/9/18.
 */

public class Mywebview extends WebView {
    public Mywebview(Context context) {
        super(context);
    }


    private double nLenStart = 0;//监听 WebView所用手势


//
//    @Override
//    public boolean onTouchEvent( MotionEvent event) {
//        int nCnt = event.getPointerCount();
//        int n = event.getAction();
//        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == nCnt)//<span style="color:#ff0000;">2表示两个手指</span>
//        {
//            for (int i = 0; i < nCnt; i++) {
//                float x = event.getX(i);
//                float y = event.getY(i);
//                Point pt = new Point((int) x, (int) y);
//            }
//            int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
//            int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
//            nLenStart = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
//        } else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP && 2 == nCnt) {
//            for (int i = 0; i < nCnt; i++) {
//                float x = event.getX(i);
//                float y = event.getY(i);
//                Point pt = new Point((int) x, (int) y);
//            }
//            int xlen = Math.abs((int) event.getX(0) - (int) event.getX(1));
//            int ylen = Math.abs((int) event.getY(0) - (int) event.getY(1));
//
//            double nLenEnd = Math.sqrt((double) xlen * xlen + (double) ylen * ylen);
//
//            if (nLenEnd > nLenStart)//通过两个手指开始距离和结束距离，来判断放大缩小
//            {
//                Toast.makeText(getApplicationContext(), "放大", Toast.LENGTH_SHORT).show();
//                tv_web_danGe.getSettings().setTextSize(WebSettings.TextSize.LARGER);
//                tv_web_danGe.reload();
//                tv_web_danGe.loadDataWithBaseURL(null, getHtmlData(content),"text/html","UTF-8",null);//显示WebView
//            } else {
//                Toast.makeText(getApplicationContext(), "缩小", Toast.LENGTH_SHORT).show();
//                tv_web_danGe.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
//                tv_web_danGe.reload();
//                tv_web_danGe.loadDataWithBaseURL(null, getHtmlData(content),"text/html","UTF-8",null);//显示WebView
//            }
//        }
//        return false;
//    }



}
