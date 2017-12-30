package com.lzy.mywheels.ExcelRead;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lzy.mywheels.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadTwoActivity extends AppCompatActivity {


    @BindView(R.id.webview)
    WebView webview;

    private int sign;
    private String location="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_two);
        ButterKnife.bind(this);
        sign = getIntent().getIntExtra("sign",1);
        String s = "";
        switch (sign){
            case 1:
                location = "qq.test.51zhiyuan.net/file/金官站接口详情.docx";

                //自适应屏幕
                webview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
                break;
            case 2:
                location = "qq.test.51zhiyuan.net/file/腾讯软件PPT.pptx";
                //自适应屏幕
                webview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
                break;
            case 3:
                location = "qq.test.51zhiyuan.net/file/三 电力监控系统安全系统安全防护要求-1 管理安全.xlsx";
                webview.setInitialScale(70);
                break;
        }

//        try {
//            s = new String(location.getBytes("ISO8859-1"),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        try {
            s = URLEncoder.encode(location, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String Url = "http://view.officeapps.live.com/op/view.aspx?src=" + s;




        webview.setVisibility(View.VISIBLE);

        webview.setWebViewClient(new AppWebViewClients());
        //支持JS
        WebSettings settings = webview.getSettings();

        settings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);

//        settings.setLoadWithOverviewMode(true);
//        settings.setUseWideViewPort(true);

//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setLoadWithOverviewMode(true);
        // https://view.officeapps.live.com/op/view.aspx?src
//       String baidu = "http://www.baidu.com";
//        String Url = "http://10.10.10.192:8080/ZhiChuang/style/file/test3.html";
        webview.loadUrl(Url);

//        webview.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    String progress = newProgress + "%";
//                    progress.setText(progress);
//                } else {
//
//                }
//            }
//        });


        webview.setOnTouchListener(new View.OnTouchListener() {

            private float OldX1, OldY1, OldX2, OldY2;
            private float NewX1, NewY1, NewX2, NewY2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_POINTER_2_DOWN:
                        if (event.getPointerCount() == 2) {
                            for (int i = 0; i < event.getPointerCount(); i++) {
                                if (i == 0) {
                                    OldX1 = event.getX(i);
                                    OldY1 = event.getY(i);
                                } else if (i == 1) {
                                    OldX2 = event.getX(i);
                                    OldY2 = event.getY(i);
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (event.getPointerCount() == 2) {
                            for (int i = 0; i < event.getPointerCount(); i++) {
                                if (i == 0) {
                                    NewX1 = event.getX(i);
                                    NewY1 = event.getY(i);
                                } else if (i == 1) {
                                    NewX2 = event.getX(i);
                                    NewY2 = event.getY(i);
                                }
                            }
                            float disOld = (float) Math.sqrt((Math.pow(OldX2 - OldX1, 2) + Math.pow(
                                    OldY2 - OldY1, 2)));
                            float disNew = (float) Math.sqrt((Math.pow(NewX2 - NewX1, 2) + Math.pow(
                                    NewY2 - NewY1, 2)));
                            Log.d("onTouch", "disOld=" + disOld + "|disNew=" + disNew);
                            if (disOld - disNew >= 25) {
                                // 缩小
                                webview.zoomOut();

                            } else if (disNew - disOld >= 25) {
                                // 放大
                                webview.zoomIn();
                            }
                            OldX1 = NewX1;
                            OldX2 = NewX2;
                            OldY1 = NewY1;
                            OldY2 = NewY2;
                        }
                    case MotionEvent.ACTION_UP:
                        if (event.getPointerCount() < 2) {
                            OldX1 = -1;
                            OldY1 = -1;
                            OldX2 = -1;
                            OldY2 = -1;
                        }
                }

                return false;
            }

        });


    }






    public class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
//            super.onPageFinished(view, url);
        }
    }


}
