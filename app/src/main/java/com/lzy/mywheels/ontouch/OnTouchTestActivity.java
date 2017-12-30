package com.lzy.mywheels.ontouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.mywheels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnTouchTestActivity extends AppCompatActivity {
    private static final String TAG = "OnTouchTestActivity";

    @BindView(R.id.tv_touch)
    TextView tvTouch;
    @BindView(R.id.bt_touch)
    Button btTouch;
    @BindView(R.id.iv_touch)
    ImageView ivTouch;

    private ViewGroup v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch_test);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //事件传递的顺序是先经过onTouch，再传递到onClick。

        //如果返回true  则直接拦截了
        //(只是表面，在onTouchEvent中已经篡改了,因为他是最底端了，view的dispatchTouch执行OnTouchEvent
        //但是他下面还有一个OnClick 方法，当返回true ,OnClick方法就不会执行了
        tvTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG, "onTouch execute, action " + motionEvent.getAction());
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
        ivTouch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "ImageView onTouch: "+event.getAction());
                return false;
            }
        });




        tvTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


}
