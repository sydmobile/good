package com.syd.good.network;


import com.syd.good.constant.AppConstant;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 管理类
 */
class RetrofitManager {

    // 网络请求的超时时间
    private static final long DEFAULT_TIME_OUT = 30;
    private HashMap<String, Retrofit> mRetrofits = new HashMap<>();

    private RetrofitManager() {
    }

    static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    /**
     * 根据传入的服务器 url 获取对应的 Retrofit
     */
    @SuppressWarnings({"ConstantConditions", "SameParameterValue"})
    Retrofit obtainRetrofit(String baseUrl) {
        Retrofit retrofit = mRetrofits.get(baseUrl);
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                if (retrofit == null) {
                    retrofit = createRetrofit(baseUrl);
                    mRetrofits.put(baseUrl, retrofit);
                }
            }
        }
        return retrofit;
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 配置OKHttpClient
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (AppConstant.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        // 连接不上是否重连
        return builder
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true) // 连接不上是否重连
                .build();
    }

    private static final class RetrofitManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

}
