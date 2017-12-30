package com.lzy.mywheels.App;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import butterknife.ButterKnife;


/**
 * Created by zidan on 2017/8/30.
 */
public abstract class BaseActivity extends FragmentActivity {
    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(getLayoutId());
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
        initTitle();
        initView();
        initDate();
        ActivityCollector.addActivty(this);
    }

    protected  abstract  void initContentView(Bundle savedInstanceState);


    protected abstract void initTitle();

    protected abstract void initView();

    protected abstract void initDate();



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivty(this);
    }


}
