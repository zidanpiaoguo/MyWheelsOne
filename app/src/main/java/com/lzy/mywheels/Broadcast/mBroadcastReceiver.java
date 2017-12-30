package com.lzy.mywheels.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * Created by bullet on 2017/12/25.
 */

public class mBroadcastReceiver extends android.content.BroadcastReceiver {

    private static final String TAG = "mBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getExtras().getString("sign");
        Log.d(TAG, "onReceive: 原版收到"+name);

        Log.d(TAG, "onReceive: "+intent.getData());

        Log.d(TAG, "onReceive:content "+context.getPackageName());
        Intent intent1 = new Intent(context,MyServivice.class);
        context.startService(intent1);
    }


}
