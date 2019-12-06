package com.android.zxb.engine.net.frame;

import com.android.zxb.engine.net.ApiConfig;
import com.android.zxb.engine.net.entity.BaseResponse;
import com.android.zxb.engine.util.helper.Slog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLProtocolException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * 一个Observer的封装类
 * <p>
 * 1 我们的网络接口返回结果结构都是类似的，从BaseResponse派生；
 * 2 把error转换成具体的error code，通过onNext返回出去，这样上层就只要提供一个
 * 回调方法就行了。
 */
public class BaseRspObserver<T> extends Subscriber<T> {

    private static final String TAG = BaseRspObserver.class.getSimpleName();
    protected Class<?> entityClass;
    private Action1<T> action;

    public BaseRspObserver(Class<?> clazz, Action1<T> action) {
        this.entityClass = clazz;
        this.action = action;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        int code;
        String msg = "";
        if (e instanceof ConnectException) {
            code = ApiConfig.ErrorCode.CONNECTION_EXCEPTION;
            msg = "连接错误,请检查网络";
        } else if (e instanceof HttpException) {
            HttpException ex = (HttpException) e;

            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                BaseResponse errorRespone = getErrorRespone(body.string());
                code = errorRespone.code;
                msg = "" + errorRespone.msg;
            } catch (IOException e1) {
                e1.printStackTrace();
                code = ex.code();
                msg = ex.message();
            } catch (JSONException e1) {
                e1.printStackTrace();
                code = ex.code();
                msg = ex.message();
            }
        } else if (e instanceof SocketTimeoutException) {
            code = ApiConfig.ErrorCode.SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof SSLHandshakeException || e instanceof SSLProtocolException) {
            code = ApiConfig.ErrorCode.SSL_HAND_SHAKE_EXCEPTION;
        } else if (e instanceof JSONException || e instanceof ClassCastException) {
            code = ApiConfig.ErrorCode.JSON_EXCEPTION;
        } else if (e instanceof UnknownHostException) {
            code = ApiConfig.ErrorCode.CONNECTION_EXCEPTION;
        } else {
            e.printStackTrace();
            Slog.e(TAG, "error=" + e.getMessage());
            code = ApiConfig.ErrorCode.OTHER_EXCEPTION;
            msg = "other exception";
        }
        try {
            T t = (T) entityClass.newInstance();
            Slog.json(t);
            if (t instanceof BaseResponse) {
                ((BaseResponse) t).code = code;
                ((BaseResponse) t).msg = msg;
            }
            onNext(t);
        } catch (Exception e1) {
            Slog.e(TAG, "onError:" + e1);
            e1.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {
        Slog.json(t);
        if (t instanceof BaseResponse) {
            BaseResponse rsp = (BaseResponse) t;
            switch (rsp.code) {
                case ApiConfig.ErrorCode.TOKEN_INVALID:
                case ApiConfig.ErrorCode.TOKEN_EXPIRED:
                    break;
                default:
                    break;
            }
        }
        Slog.d(TAG, "onNext");
        if (action != null) {
            action.call(t);
            action = null;//防止重复调用Action方法
        }
    }

    //http请求在状态码非200时解析response里的body
    private BaseResponse getErrorRespone(String error) throws JSONException {
        BaseResponse bs = new BaseResponse();
        JSONObject jsonObject = new JSONObject(error);
        int code = jsonObject.getInt("code");
        String msg = jsonObject.getString("msg");
        Object ob = jsonObject.get("data");
        bs.code = code;
        bs.msg = msg;
        bs.data = ob;
        return bs;
    }
}