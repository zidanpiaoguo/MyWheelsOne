package com.tianhui.test.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


/**
 * Created by zidan on 2017/9/12.
 */

public class MyApplication extends Application {
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

    }

    public static MyApplication getInstance() {

        return sInstance;
    }

//
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        if(!quickStart()){
//
//            if(needWait(base)){
//
//                waitForDexopt(base);
//            }
//        }
//    }
//
//
//    public boolean quickStart(){
//        if(StringUtils.contains(getCurProcessName(this),":mini")){
//            return true;
//        }
//        return false;
//    }
//
//
//    private boolean needWait(Context context){
//        String flag = get2thDexSHA1(context);
////        SharedPreferences sp = context.getSharedPreferences(P);
////        String saveValue = sp.getString(Key_DEX2_SHA1,"");
////        return !StringUtils.equals(flag,saveValue);
//        return false;
//    }
//
//
//    private String get2thDexSHA1(Context context){
//        ApplicationInfo ai = context.getApplicationInfo();
//        String source = ai.sourceDir;
//        try {
//            JarFile jarFile = new JarFile(source);
//            Manifest mf= jarFile.getManifest();
//            Map<String,Attributes> map = mf.getEntries();
//            Attributes a = map.get("classes2.dex");
//            return a.getValue("SHA1-Digest");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public static String getCurProcessName(Context context){
//        int pid = android.os.Process.myPid();
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningAppProcessInfo appProcessInfo:activityManager.getRunningAppProcesses()){
//            if(appProcessInfo.pid == pid){
//                return appProcessInfo.processName;
//            }
//        }
//        return null;
//    }
//
//
//    public void waitForDexopt(Context base){
//        Intent intent = new Intent();
//        ComponentName componetName = new ComponentName("",LoadResActivity.class.getName());
//        intent.setComponent(componetName);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        base.startActivity(intent);
//        long startWait = System.currentTimeMillis();
//        long waitTime =10*1000;
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1){
//            waitTime = 20*1000;
//        }
//        while (needWait(base)){
//
//            try {
//                long newWait = System.currentTimeMillis()-startWait;
//                if (newWait>=waitTime){
//                    return;
//                }
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
