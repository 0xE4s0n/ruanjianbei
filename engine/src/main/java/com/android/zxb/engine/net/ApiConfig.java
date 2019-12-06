package com.android.zxb.engine.net;

/**
 * 网络相关的配置常量.
 */

public interface ApiConfig {

    //  String BASE_URL = "http://192.168.43.221:8200/";
    String BASE_URL = "https://gongwei-api.julu666.com/";
    String BASE_VERSION = "v1";
    int PAGE_SIZE = 10;

    /**
     * 请求方法
     */
    interface Method {

        String GET = "GET";
        String POST = "POST";
        String DELETE = "DELETE";
        String PUT = "PUT";
        String PATCH = "PATCH";
        String HEAD = "HEAD";
    }

    /**
     *
     */

    interface Status {
        int NO = 0;
        int YES = 1;
    }

    /**
     * header信息
     */
    interface Header {

        String KEY_MASTER_KEY = "master_key";
        String KEY_TOKEN = "token";
        String KEY_AUTHORIZATION = "authorization";
        String KEY_ACCOUNT_ID = "account_id";
        String KEY_CONTENT_TYPE = "Content-Type";
        String KEY_DEVICE = "device";//Android6.0;Xiaomi Redmi Note 3;1080*1920;智慧百中1.1

        String VALUE_APPLICATION_JSON = "application/json";
        String VALUE_MULTIPART_FORM_DATA = "multipart/form-data";
        String VALUE_MASTER_KEY = "abcdefghijkopqrstuvwxyz123456";
    }

    /**
     * 错误码
     */
    interface ErrorCode {

        int SUCCESS = 200;
        int DATA_EMPTY = -1000;
        int INVALIDATE_INFO = -2000;//无效数据
        int OTHER_EXCEPTION = -99;
        int CONNECTION_EXCEPTION = -100;
        int SOCKET_TIMEOUT_EXCEPTION = -101;
        int SSL_HAND_SHAKE_EXCEPTION = -102;
        int JSON_EXCEPTION = -103;

        int SERVER_BAD_GATEWAY = 502;
        int SERVER_EXCEPTION = 500;

        int TOKEN_INVALID = 600;
        int TOKEN_EXPIRED = 601;
    }
}
