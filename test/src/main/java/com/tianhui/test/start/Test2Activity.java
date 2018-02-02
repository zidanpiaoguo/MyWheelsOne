package com.tianhui.test.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tianhui.test.R;

import java.util.Random;

public class Test2Activity extends AppCompatActivity {

    private Button btText;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
        init();
    }

    private void init() {
        btText.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Test2Activity.this,Text1Activity.class);
                startActivity(intent);
            }
        });
        tvText.setText(new Random().nextInt(100)+"");
    }

    private void initView() {
        btText = (Button) findViewById(R.id.bt_text);
        tvText = (TextView) findViewById(R.id.tv_text);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
