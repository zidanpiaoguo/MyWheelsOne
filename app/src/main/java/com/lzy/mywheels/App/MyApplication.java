package com.lzy.mywheels.App;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Created by zidan on 2017/9/12.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);

    }

    public static MyApplication getInstance() {

        return sInstance;
    }


//    public void setLogin(boolean islogin) {
//        SPUtils.saveBoolean(this, "is_login", islogin);
//    }
//
//    public boolean isLogin() {
//        return SPUtils.getBoolean(this, "is_login", false);
//    }



}
