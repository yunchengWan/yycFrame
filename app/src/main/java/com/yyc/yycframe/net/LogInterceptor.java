package com.yyc.yycframe.net;

import android.support.annotation.NonNull;

import com.yyc.yycframe.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by chenghe.zhang on 18-8-20.
 * Email:chenghe.zhang@sioeye.com
 * 打印网络调用及反馈日志的拦截器
 */

class LogInterceptor implements Interceptor {

    private static final String TAG = "LogInterceptor";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * body 是否经过编码(gzip or compress or deflate)
     *
     * @param headers the response header
     * @return whether or not has compress
     */
    private static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        long requestTime = System.currentTimeMillis();
        HttpUrl httpUrl = request.url();
        LogUtil.i(TAG, "Send request to " + httpUrl);
        LogUtil.i(TAG, "Request Header is: \n" + request.headers());

        Response response = chain.proceed(request);
        long responseTime = System.currentTimeMillis();
        LogUtil.d(TAG, String.format(Locale.ENGLISH, "Received response for %s in %.1fms%n%s",
                httpUrl, (responseTime - requestTime) / 1000F, response.headers()));
        ResponseBody body = response.body();
        if (body == null) {
            LogUtil.d(TAG, "Body Null.");
        } else if (bodyEncoded(response.headers())) {
            LogUtil.d(TAG, "Body is Encode.");
        } else {
            LogUtil.d(TAG, "Body:" + response.toString());

            BufferedSource source = body.source();
            long contentLength = body.contentLength();

            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = body.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.
                    return response;
                }
            }

            try {
                if (contentLength != 0) {
                    String result = buffer.clone().readString(charset);

                    //获取到response的body的string字符串
                    LogUtil.d(TAG, String.format("body: %s", result));
                }

                LogUtil.d(TAG, "END HTTP (" + buffer.size() + "-byte body)");
            } catch (Throwable ex) {
                LogUtil.e(TAG, "Output Log error.");
            }
        }

        return response;
    }

}
