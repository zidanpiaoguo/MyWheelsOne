package com.lzy.mywheels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.mywheels.Broadcast.RecycleViewActivity;
import com.lzy.mywheels.counttime.TimeActivity;
import com.lzy.mywheels.ijkplayer.PlayerActivity;
import com.lzy.mywheels.viewtest.TestModleActivity;
import com.lzy.mywheels.update.UpDateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bt_2)
    Button bt2;
    @BindView(R.id.bt_3)
    Button bt3;
    @BindView(R.id.bt_1)
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpDateActivity.class);
//                intent.putExtra("sign",1);

                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestModleActivity.class);

//                intent.putExtra("sign",2);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
//                intent.putExtra("sign",3);
                startActivity(intent);
            }
        });

    }




}



