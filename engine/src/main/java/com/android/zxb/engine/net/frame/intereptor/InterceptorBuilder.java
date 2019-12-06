package com.android.zxb.engine.net.frame.intereptor;


import com.android.zxb.engine.util.helper.Slog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * 网络拦截器的Builder类.
 */

public class InterceptorBuilder {

    private static InterceptorBuilder sInstance;
    //网络请求到内部拦截器之前的拦截器类
    private List<Interceptor> interceptors;
    //内部实现请求之前的拦截器
    private List<Interceptor> netInterceptors;

    private InterceptorBuilder() {
        interceptors = new ArrayList<>();
        netInterceptors = new ArrayList<>();
        //添加默认的拦截器
        addInterceptorBuilder(new LoggerInterceptor());
        addNetInterceptorBuilder(new LoggerInterceptor());
    }

    /**
     * 返回实例
     */
    public static InterceptorBuilder getInstance() {
        synchronized (InterceptorBuilder.class) {
            if (sInstance == null) {
                synchronized (InterceptorBuilder.class) {
                    sInstance = new InterceptorBuilder();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加网络拦截器
     */
    public static void addNetInteceptor(Interceptor interceptor) {
        getInstance().addNetInterceptorBuilder(interceptor);
    }

    /**
     * 添加网络请求的拦截器
     */
    public void addInterceptorBuilder(Interceptor interceptor) {
        if (interceptors.contains(interceptor)) {
            Slog.e(InterceptorBuilder.class, "拦截器已经被添加过");
        } else {
            interceptors.add(interceptor);
        }
    }

    /**
     * 添加网络请求的拦截器
     */
    public void addNetInterceptorBuilder(Interceptor netInterceptor) {
        if (netInterceptors.contains(netInterceptor)) {
            Slog.e(InterceptorBuilder.class, "拦截器已经被添加过");
        } else {
            netInterceptors.add(netInterceptor);
        }
    }

    /**
     * 添加网络请求的拦截器
     */
    public void removeInterceptorBuilder(Interceptor interceptor) {
        if (!interceptors.contains(interceptor)) {
            Slog.e(InterceptorBuilder.class, "拦截器没有被添加过");
        } else {
            interceptors.remove(interceptor);
        }
    }

    /**
     * 添加网络请求的拦截器
     */
    public void removeNetInterceptorBuilder(Interceptor netInterceptor) {
        if (!netInterceptors.contains(netInterceptor)) {
            Slog.e(InterceptorBuilder.class, "拦截器没有被添加过");
        } else {
            netInterceptors.remove(netInterceptor);
        }
    }

    /**
     * 清空攔截器
     */
    public void clearInterceptor() {
        interceptors.clear();
    }

    /**
     * 清空网络攔截器
     */
    public void clearNetInterceptor() {
        netInterceptors.clear();
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public List<Interceptor> getNetInterceptors() {
        return netInterceptors;
    }
}
