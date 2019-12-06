package com.android.zxb.engine.base.subscription;

import androidx.annotation.NonNull;

import rx.Subscription;

/**
 * Sloth框架：RxJava相关
 */

public interface SlothSubscription {

    /**
     * 取消订阅
     */
    void unSubscribe();

    /**
     * 添加至RxSubscription
     */
    void addRxSubscription(@NonNull Subscription subscription);

    /**
     * 取消RxSubscription
     */
    void removeRxSubscription(@NonNull Subscription subscription);
}
