package com.android.zxb.engine.net.frame;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Rx控制网络请求线程.
 */

public class ApiWrapper {
    public static <T> Subscription wrap(Observable<T> observable, Class<?> rspClass,
                                        Action1<T> callback) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseRspObserver<>(rspClass, callback));
    }

    public static <T> Observable<T> wrap(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
