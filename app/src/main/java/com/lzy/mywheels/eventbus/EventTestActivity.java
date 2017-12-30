package com.lzy.mywheels.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.mywheels.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventTestActivity extends AppCompatActivity {


    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.bt_message)
    Button btMessage;
    @BindView(R.id.bt_subscribe)
    Button btSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_test);
        ButterKnife.bind(this);

        btMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventTestActivity.this, SecondActivity.class));

            }
        });
        btSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EventBus.getDefault().register(EventTestActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        //注册事件
//        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMoonEvent(MessageEvent messageEvent) {
//        tvMessage.setText(messageEvent.getMessage());
//    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky=true)
    public void onTest(MessageEvent messageEvent){

        tvMessage.setText(messageEvent.getMessage());

    }

}
