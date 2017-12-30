package com.lzy.mywheels.ModleTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lzy.mywheels.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestModleActivity extends AppCompatActivity {

    @BindView(R.id.nihao)
    TextView nihao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_modle);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        nihao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            LoadingDialog dialog=new LoadingDialog(getApplicationContext());
//            dialog.canShow();

            }
        });
    }
}
