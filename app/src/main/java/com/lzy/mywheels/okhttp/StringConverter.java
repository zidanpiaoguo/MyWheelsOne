package com.lzy.mywheels.okhttp;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zidan on 2017/10/14.
 */

public class StringConverter implements Converter<ResponseBody,String> {

    public static final StringConverter INSTANCE = new StringConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
