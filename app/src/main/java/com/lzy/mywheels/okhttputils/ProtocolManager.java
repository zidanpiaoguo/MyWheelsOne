package com.lzy.mywheels.okhttputils;


import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by xiaoping on 16/2/25.
 */
public class ProtocolManager {


    public enum HttpMethod {
        POST,
        GET,
    }

    private String BASE_URL = RequestUrl.IP;

    private static ProtocolManager mInstance;

    public static ProtocolManager getInstance() {
        if (mInstance == null) {
            synchronized (ProtocolManager.class) {
                if (mInstance == null) {
                    mInstance = new ProtocolManager();
                }
            }
        }
        return mInstance;
    }

    private ProtocolManager() {
    }

    String userId;
    public void request(Map<String, String> params, String url, final ReponseCallback reponseCallback, HttpMethod httpMethod) {
//        if (!Utils.isNetworkConnected(MyApplication.getInstance())) {
//            reponseCallback.fail("请检查网络连接");
//            return;
//        }
        if (params == null) {
            params = new HashMap<>();
        }
//        params.put("token", Utils.getDeviceId(MyApplication.getInstance()));

//        params.put("sign", "ANDROID");

//        params.put("timestamp", Utils.getSTime());
//        params.put("version", Utils.getVersion());

//        if (!MyApplication.getInstance().isLogin()){
//            userId = "";
//            params.put("userId", userId);
//
//        }else {
//
////
//        }



        Gson gson = new Gson();
        String jsonString = gson.toJson(params);
        String s = new String(jsonString.getBytes());
        Log.d("lzy", "request: "+jsonString);


        OkHttpUtils.postString()
                .url(BASE_URL + url)
                .content(jsonString)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        reponseCallback.fail("链接超时，请稍后重试");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.indexOf("Exception") > 0 || response.indexOf("exception") > 0) {
                            reponseCallback.fail("链接超时，请稍后重试");
                        } else {
                            reponseCallback.success(response);
                        }
                    }
                });
    }




    public interface ReponseCallback {
        void fail(String msg);

        void success(String response);

    }
}
