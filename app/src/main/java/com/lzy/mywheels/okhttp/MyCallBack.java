package com.lzy.mywheels.okhttp;

import retrofit2.Call;

/**
 * Created by zidan on 2017/10/13.
 */

public interface MyCallBack<T> {


    void onResponse(Call<T> call, String response);



    void onFailure(Call<T> call, Throwable t);

}
