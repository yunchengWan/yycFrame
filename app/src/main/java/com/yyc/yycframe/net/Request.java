package com.yyc.yycframe.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 所有请求基类 可以做一些统一处理
 */
public class Request implements IApiMethod {

    private IApiMethod mApi;

    public Request(RetrofitManager manager) {
        mApi = manager.newApi(IApiMethod.class);
    }

    @Override
    public Observable<String> doGetRequest(String api, Map<String, Object> para) {
        return mApi.doGetRequest(api, para)
                .compose(RxSchedulers.commonScheduler());
    }

    @Override
    public Observable<String> doPostRequest(String api, Map<String, Object> para) {
        return mApi.doPostRequest(api, para)
                .compose(RxSchedulers.commonScheduler());
    }

    @Override
    public Observable<String> doPostRequestByJson(String api, RequestBody requestBody) {
        return mApi.doPostRequestByJson(api, requestBody)
                .compose(RxSchedulers.commonScheduler());
    }

    @Override
    public Observable<String> doPutRequestByJson(String api, RequestBody requestBody) {
        return mApi.doPutRequestByJson(api, requestBody)
                .compose(RxSchedulers.commonScheduler());
    }

    @Override
    public Observable<String> doPutUploadByFullUrl(String url, MultipartBody.Part file) {
        return mApi.doPutUploadByFullUrl(url, file)
                .compose(RxSchedulers.commonScheduler());
    }
}
