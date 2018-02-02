package com.lzy.mywheels.counttime;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzy.mywheels.App.MyToast;
import com.lzy.mywheels.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zidan on 2017/10/11.
 */


public class TimeActivity extends Activity {

    private int i=0;

    private Timer timer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        init();
    }

    private void init() {

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyToast.makeText(""+(i++));
                    }
                });
            }
        };

        timer.schedule(timerTask,0,1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
