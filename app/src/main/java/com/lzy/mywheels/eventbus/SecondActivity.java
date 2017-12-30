package com.lzy.mywheels.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.mywheels.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tv_two_message)
    TextView tvTwoMessage;
    @BindView(R.id.bt_two_message)
    Button btTwoMessage;
    @BindView(R.id.bt_send_sticky)
    Button btSendSticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        btTwoMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("我篡改了一个内容"));
                finish();

            }
        });
        btSendSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MessageEvent("我已经被粘性事件篡改"));
                finish();
            }
        });


    }
}
