package com.lzy.mywheels.okhttputils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.lzy.mywheels.App.MyApplication;

import java.util.Date;

/**
 * Created by zidan on 2017/9/11.
 */

public class Utils {

    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获取 设备 ID 号
     *
     * @param content
     * @return
     */
    public static String getDeviceId(Context content) {

        TelephonyManager tm = (TelephonyManager) content.getSystemService(content.TELEPHONY_SERVICE);
        return tm.getDeviceId();//获取设备id
    }


    /**
     * 判断当前是否有网络链接
     *
     * @param context
     * @return
     */

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        }
        return false;
    }


    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getSTime() {
        String stime = new Date().getTime() + "";
        return stime;
    }



    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = MyApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }




}
