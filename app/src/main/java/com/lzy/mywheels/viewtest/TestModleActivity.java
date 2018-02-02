package com.lzy.mywheels.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.mywheels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestModleActivity extends AppCompatActivity {

    @BindView(R.id.nihao)
    TextView nihao;
    @BindView(R.id.ll_touch_event)
    LinearLayout llTouchEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_modle);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {






    }


}
