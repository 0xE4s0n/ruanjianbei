package com.android.zxb.engine.net.callback;

import com.android.zxb.engine.net.ApiConfig.ErrorCode;

/**
 * 响应的数据实体.
 */

public final class ResponseInfo<T> {

    /**
     * 请求状态码
     */
    public final int code;
    /**
     * 错误原因
     */
    public final String error;
    /**
     * 请求返回的body
     */
    public final T body;

    public ResponseInfo(int code, String error, T body) {
        this.code = code;
        this.error = error;
        this.body = body;
    }

    /**
     * 是否成功
     */
    public boolean isOK() {
        return code == ErrorCode.SUCCESS;
    }
}
