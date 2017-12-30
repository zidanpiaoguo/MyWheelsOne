package com.lzy.mywheels.Broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.mywheels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity {

    private static final String TAG = "RecycleViewActivity";
    @BindView(R.id.bt_send_broadcast)
    Button btSendBroadcast;
    mBroadcastReceiver btSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        btSend =new mBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lzy");
        registerReceiver(btSend,filter);
        
        btSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: ");
                Intent intent =new Intent();
                intent.setAction("com.lzy");
                intent.putExtra("sign","你懂得");
                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(btSend);

    }


    //
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        //销毁在onResume()方法中的广播
//        unregisterReceiver(mBroadcastReceiver);
//    }


}
