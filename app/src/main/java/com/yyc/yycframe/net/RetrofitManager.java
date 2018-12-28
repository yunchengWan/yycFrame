package com.yyc.yycframe.net;

import com.yyc.yycframe.Constant;
import com.yyc.yycframe.LogUtil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {

    /**
     * 请求日志拦截器
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                message -> LogUtil.d(Constant.REQUEST_TAG, message)
        );
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 请求头拦截器
     */
    private Interceptor getHeaderInterceptor() {
        return chain -> {
            Request originRequest = chain.request();
            Request.Builder originBuilder = originRequest.newBuilder();

            /*-------add Headers------------*/
            //originBuilder.addHeader("", "");

            Request.Builder newBuilder = originBuilder.method(originRequest.method(), originRequest.body());
            Request newRequest = newBuilder.build();
            return chain.proceed(newRequest);
        };
    }

    /**
     * 配置okhttp Client
     */
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getHeaderInterceptor())
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    /**
     * 构建retrofit
     */
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 创建api
     */
    public <T> T newApi(Class<T> cls) {
        return getRetrofit().create(cls);
    }
}
