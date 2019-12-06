// (c)2016 Flipboard Inc, All Rights Reserved.

package com.android.zxb.engine.net.frame;

import android.os.RecoverySystem.ProgressListener;

import androidx.annotation.NonNull;

import com.android.zxb.engine.net.ApiConfig;
import com.android.zxb.engine.net.frame.intereptor.InterceptorBuilder;
import com.android.zxb.engine.net.frame.network.HttpManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class Api {

    private static Api sApi;
    private static String BASE_URL;
    private Retrofit mRetrofit = null;

    private final static int CONNECT_TIMEOUT = 60;
    private final static int READ_TIMEOUT = 100;
    private final static int WRITE_TIMEOUT = 120;

    private Api() {
    }

    /**
     * 返回Api单例对象
     */
    public static Api get() {
        if (sApi == null) {
            synchronized (Api.class) {
                if (sApi == null) sApi = new Api();
            }
            sApi = new Api();
        }
        BASE_URL = ApiConfig.BASE_URL;
        return sApi;
    }

    public static Api get(String baseUrl) {
        if (sApi == null) {
            synchronized (Api.class) {
                if (sApi == null) sApi = new Api();
            }
            sApi = new Api();
        }
        BASE_URL = baseUrl;
        return sApi;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit(OkHttpClient client) {
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(HttpManager.sGsonConverterFactory)
                .addCallAdapterFactory(HttpManager.sRxJavaCallAdapterFactory)
                .build();
    }

    /**
     * 设置OKHttp拦截器
     */
    public OkHttpClient.Builder getHttpClient() {
        InterceptorBuilder interceptorBuilder = InterceptorBuilder.getInstance();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);//设置连接超时时间

        //在此拦截SSL验证问题
        allowAllSSL(builder);

        //添加拦截器和网络拦截器
        List<Interceptor> interceptors = interceptorBuilder.getInterceptors();
        List<Interceptor> netInterceptors = interceptorBuilder.getNetInterceptors();

        //添加http_log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(logging);

        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        if (netInterceptors != null && netInterceptors.size() > 0) {
            for (Interceptor interceptor : netInterceptors) {
                builder.addNetworkInterceptor(interceptor);
            }
        }
        return builder;
    }

    /**
     * 返回Api接口执行类
     *
     * @param tClass api接口
     * @return api执行类
     */
    public synchronized <T> T getApi(@NonNull Class<T> tClass) {
        OkHttpClient.Builder httpClient = getHttpClient();
        httpClient.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.addHeader(ApiConfig.Header.KEY_CONTENT_TYPE, ApiConfig.Header.VALUE_APPLICATION_JSON);
            return chain.proceed(request);
        });
        initRetrofit(httpClient.build());
        return mRetrofit.create(tClass);
    }

    /**
     * 允许所有的SSL
     */
    private void allowAllSSL(OkHttpClient.Builder client) {
        TrustManager[] trustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                            CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws
                            CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManager, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            client.sslSocketFactory(sslSocketFactory);
            client.hostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回下载相关的Api
     *
     * @param tClass   api接口
     * @param listener 下载进度监听器
     * @return Api接口执行类
     */
    public synchronized <T> T getDownloadApi(Class<T> tClass, ProgressListener listener) {
        return mRetrofit.create(tClass);
    }

    /**
     * 获取上传的Api
     */
    public synchronized <T> T getUploadApi(@NonNull Class<T> tClass) {
        OkHttpClient.Builder httpClient = getHttpClient();
        httpClient.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
//            builder.removeHeader(ApiConfig.Header.KEY_CONTENT_TYPE);
            return chain.proceed(request);
        });
        initRetrofit(httpClient.build());
        return mRetrofit.create(tClass);
    }

    public String getAboutUrl() {
        return "file:///android_asset/drive/about.html";
        //BASE_URL + "/about/about";
    }

    public String getEulaUrl() {
        return "file:///android_asset/drive/eula.html";
        //BASE_URL + "about/eula";
    }

    public String getManualUrl() {
        return "file:///android_asset/drive/manual.html";
        //BASE_URL + "about/eula";
    }
}
