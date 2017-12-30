package com.lzy.mywheels.okhttp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zidan on 2017/10/13.
 */

public interface Request {

    @POST("userLogin.do")
    @FormUrlEncoded
    Call<UserData> login(@Field("username") String username,
                       @Field("password")String password);


    @POST("massage.do")
    Call<Message> getMessage();

    @POST("user/new")
    Call<String> creatUser(@Body String s);


}
