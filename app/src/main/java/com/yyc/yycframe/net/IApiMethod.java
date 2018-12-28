package com.yyc.yycframe.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 需要的方法接口
 */
public interface IApiMethod {

    @GET("{api}")
    Observable<String> doGetRequest(@Path(value = "api", encoded = true) String api, @QueryMap Map<String, Object> para);

    @FormUrlEncoded
    @POST("{api}")
    Observable<String> doPostRequest(@Path(value = "api", encoded = true) String api, @FieldMap Map<String, Object> para);

    @POST("{api}")
    Observable<String> doPostRequestByJson(@Path(value = "api", encoded = true) String api, @Body RequestBody requestBody);

    @PUT("{api}")
    Observable<String> doPutRequestByJson(@Path(value = "api", encoded = true) String api, @Body RequestBody requestBody);

    @Multipart
    @PUT()
    Observable<String> doPutUploadByFullUrl(@Url String url, @Part MultipartBody.Part file);
}
