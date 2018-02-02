package com.lzy.mywheels.Broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lzy.mywheels.MainActivity;
import com.lzy.mywheels.R;

/**
 *
 * Created by bullet on 2017/12/25.
 */

public class MyServivice extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getNotification();
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }



    public void getNotification() {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this,MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("您有一个消息")
                .setContentTitle("苹果")
                .setContentText("这个是一个传奇故事，有什么内容呢，我只知道他不是这样的。")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        manager.notify(1,builder.build());




//        //得到NotificationManager的对象，用来实现发送Notification
//        NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
//
//
//
//        Notification.Builder notifi = new Notification.Builder(getApplication());
//
//
//        notifi.setSmallIcon(R.mipmap.ic_launcher);
//        notifi.setTicker("标题");        //设置消息来时显示的消息
//        notifi.setContentTitle("内容标题");
//        notifi.setContentText("内容");
//
//
//        notifi.setWhen(System.currentTimeMillis()); // 发送时间
//
//        notifi.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
//
//        notifi.setAutoCancel(true);
//
//
//        Intent intent = new Intent(getApplication(),MainActivity.class);
//        //参数：1、上下文 2、请求码 3、用于启动的intent 4、新开启的Activity的启动模式
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
//
//        notifi.setContentIntent(pendingIntent);
//        Notification notification1 = notifi.build();
//
//
//        manager.notify(124, notification1); // 通过通知管理器发送通知
//
//        //取消通知
//        //manager.cancelAll();







    }

}
