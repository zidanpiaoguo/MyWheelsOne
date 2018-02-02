package com.tianhui.test.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tianhui.test.R;

import java.util.Random;

public class Text1Activity extends AppCompatActivity {

    private Button btText;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text1);
        initView();
        init();
    }

    private void init() {
        btText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.getHandler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // 登录成功,跳转到主页
                        Intent intent =new Intent(Text1Activity.this,Test2Activity.class);
                        startActivity(intent);
                    }
                }, 3000);

            }
        });
        tvText.setText(new Random().nextInt(100)+"");
    }


    private void initView() {
        btText = (Button) findViewById(R.id.bt_text);
        tvText = (TextView) findViewById(R.id.tv_text);
    }


}
