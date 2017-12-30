package com.lzy.mywheels.okhttputils;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.mywheels.App.BaseActivity;
import com.lzy.mywheels.R;
import com.lzy.mywheels.App.MyToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class OkhttpActivity extends BaseActivity {

    @BindView(R.id.login)
    TextView login;

    private Map param  = new HashMap();

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_okhttp);
    }

    @Override
    protected void initTitle() {


    }


    @Override
    protected void initView() {
        param.clear();
        param.put("phone","+8613656690321");
        param.put("password","a01b28fce5bb29e8e3b14a9b314acc8d");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyToast.makeText("点击了登陆！");
                ProtocolHelp.getInstance(OkhttpActivity.this).protocolHelp(param,RequestUrl.LOGIN,
                        ProtocolManager.HttpMethod.POST,Login.class,new ProtocolHelp.HttpCallBack(){

                            @Override
                            public void fail(String message) {
                                MyToast.makeText(message);
                            }

                            @Override
                            public void success(Object object) {
//                                Login data = (Login) object;
                                MyToast.makeText((String)object);
                            }
                        });



            }
        });


    }

    @Override
    protected void initDate() {

    }
}
