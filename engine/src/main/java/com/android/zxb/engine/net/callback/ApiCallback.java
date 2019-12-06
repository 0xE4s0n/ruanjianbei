package com.android.zxb.engine.net.callback;

/**
 * 网络请求返回的数据类.
 */

public interface ApiCallback<T> {

    /**
     * 请求的结果回调
     */
    void onResult(ResponseInfo<T> info);
}
