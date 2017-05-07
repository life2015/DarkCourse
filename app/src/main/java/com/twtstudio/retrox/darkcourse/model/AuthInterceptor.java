package com.twtstudio.retrox.darkcourse.model;

import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by retrox on 07/05/2017.
 */

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl.Builder builder = original.url().newBuilder();
        HttpUrl httpUrl = builder.addQueryParameter("sid", Hawk.get("sid")).build();
        Request.Builder requestBuilder = original.newBuilder().url(httpUrl);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
