package com.android.zxb.engine.net.entity;

/**
 * 请求响应类的基类.
 */
public class BaseResponse<T> extends BaseEntity {

    public Integer code;
    public String msg;

    public T data;

    public BaseResponse() {
    }

    public BaseResponse(int error_code, String error_msg) {
        this.code = error_code;
        this.msg = error_msg;
    }

    public BaseResponse(int error_code, String error_msg, T extra) {
        this.code = error_code;
        this.msg = error_msg;
        this.data = extra;
    }
}
