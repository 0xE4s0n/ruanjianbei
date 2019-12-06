package com.android.zxb.engine.net.frame.intereptor;


import com.android.zxb.engine.util.helper.Slog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求拦截类,打印请求和响应的Log信息.
 */

public class LoggerInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Slog.i(LoggerInterceptor.class, String.format("Method:%s\nSending request %s on %s%n%s",
                request.method(), request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Slog.i(LoggerInterceptor.class, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
