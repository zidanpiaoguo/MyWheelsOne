package com.lzy.mywheels.okhttp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.mywheels.R;
import com.lzy.mywheels.myview.LoadingDialog;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = "OkHttpActivity";

    LoadingDialog dialog;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.tv_test)
    TextView tvTest;

    private final static int COUNT =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        dialog = new LoadingDialog(OkHttpActivity.this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setTen();
//                initView();
                retrofit2();
                login();
            }
        });
    }

    private void setTen() {
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();
    }

    private void initView() {
        final Timer timer = new Timer();
        final long end = System.currentTimeMillis()+ 1000*3;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(COUNT);

            }
        },0,1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(OkHttpActivity.this,HttpSecondActivity.class);
                startActivity(intent);
                timer.cancel();
            }
        },new Date(end));
    }

    private Handler handler = new Handler(){
        int num = 2;
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case COUNT:
                    tvTest.setText(String.valueOf(num));
                    num--;
                    break;
                default:
                    break;
            }
        }
    };


    private void login() {
        String url = "http://10.10.10.192:8080/ZhiChuang/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request request = retrofit.create(Request.class);
        Call<UserData> call = request.login("admin", "admn");
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                Log.d(TAG, "normalGet:" + response.body() + "");
                UserData user = response.body();
                Log.d(TAG, "onResponse: " + user.getData().getNeckname());
            }
            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d(TAG, "normalGet:" + "error");
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private void retrofit2() {
        String url = "http://10.10.10.192:8080/ZhiChuang/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request userData = retrofit.create(Request.class);
        Call<Message> call = userData.getMessage();
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Log.d(TAG, "normalGet:" + response.body() + "");
                Message message = response.body();
                Log.d(TAG, "onResponse: " + message.getData().get(0).getMname());

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d(TAG, "normalGet:" + "error" + "");
            }
        });
    }

}
