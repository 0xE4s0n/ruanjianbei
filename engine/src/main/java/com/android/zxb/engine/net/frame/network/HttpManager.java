package com.android.zxb.engine.net.frame.network;

import android.text.TextUtils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http连接的管理器
 * <p>
 * App通过getHttpClient来创建http client，建议保存这个client，不要反复调用。
 */
public class HttpManager {

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 15000;
    // always verify the host - dont check for certificate
    private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    public static Converter.Factory sGsonConverterFactory = GsonConverterFactory.create();
    public static CallAdapter.Factory sRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static OkHttpClient sOkHttpClient = new OkHttpClient();

    // 简单的log : Level.BASIC, 详细的log: Level.BODY
    public static OkHttpClient getHttpClient() {
        return getHttpClient(HttpLoggingInterceptor.Level.BASIC);
    }

    //Fresco 自定义http
    public static OkHttpClient getFrescoHttpClient(HttpLoggingInterceptor.Level level) {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);

        SSLSocketFactory sslSocketFactory = trustAllHosts();
        builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier(DO_NOT_VERIFY);

        // 设置request 拦截器
        RequestInterceptor requestInterceptor = new RequestInterceptor();
        builder.addInterceptor(requestInterceptor);

        // 设置log拦截器
        if (level != HttpLoggingInterceptor.Level.NONE) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log leave
            logging.setLevel(level);
            //logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            // add your other interceptors …
            builder.interceptors().add(logging);
            // add logging as last interceptor
        }
        return builder.build();
    }

    public static OkHttpClient getHttpClient(HttpLoggingInterceptor.Level level) {
        return getHttpClient(null, level, null);
    }

    public static OkHttpClient getHttpClient(String cer, HttpLoggingInterceptor.Level level,
                                             Interceptor requestInterceptor) {
        SSLSocketFactory sslSocketFactory = null;
        if (!TextUtils.isEmpty(cer)) {
            sslSocketFactory = CloudSSLSocketFactory.getSSLSocketFactory(cer);
        } else {
            sslSocketFactory = trustAllHosts();
        }
        OkHttpClient.Builder builder = sOkHttpClient.newBuilder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier(DO_NOT_VERIFY);

        // 设置request 拦截器
        if (requestInterceptor == null) {
            requestInterceptor = new RequestInterceptor();
        }
        builder.addInterceptor(requestInterceptor);

        // 设置log拦截器
        if (level != HttpLoggingInterceptor.Level.NONE) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log leave
            logging.setLevel(level);
            //logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            // add your other interceptors …
            builder.interceptors().add(logging);
            // add logging as last interceptor
        }
        return builder.build();
    }

    /**
     * Trust every server - dont check for any certificate
     */
    private static SSLSocketFactory trustAllHosts() {
        SSLSocketFactory sslSocketFactory = null;
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    public void checkClientTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain,
                                                   String authType) throws CertificateException {
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

    //下载客户端，可以监听进度
    public static OkHttpClient getHttpDownloadClient(String cer, HttpLoggingInterceptor.Level level,
                                                     ProgressListener listener) {
        SSLSocketFactory sslSocketFactory = null;
        if (!TextUtils.isEmpty(cer)) {
            sslSocketFactory = CloudSSLSocketFactory.getSSLSocketFactory(cer);
        } else {
            sslSocketFactory = trustAllHosts();
        }

        OkHttpClient.Builder builder = sOkHttpClient.newBuilder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);

        builder.sslSocketFactory(sslSocketFactory);
        builder.hostnameVerifier(DO_NOT_VERIFY);

        // 设置request 拦截器
        RequestInterceptor requestInterceptor = new RequestInterceptor();
        builder.addInterceptor(requestInterceptor);
        builder.addInterceptor(new DownloadProgressInterceptor(listener));

        // 设置log拦截器
        if (level != HttpLoggingInterceptor.Level.NONE) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log leave
            logging.setLevel(level);
            //logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            // add your other interceptors …
            builder.interceptors().add(logging);
            // add logging as last interceptor
        }
        return builder.build();
    }

    /**
     * This interceptor compresses the HTTP request body. Many webservers can't handle this!
     */
    private final static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request compressedRequest = originalRequest.newBuilder()
                    .build();
            return chain.proceed(compressedRequest);
        }
    }
}
