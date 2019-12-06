package com.android.zxb.engine.net.manager;

import rx.Subscription;

/**
 * created by zhaoxiangbin on 2019/4/25 17:02
 * 460837364@qq.com
 */
public interface RxActionManager<T> {
    boolean isSubscribe(T tag);

    void add(T tag, Subscription subscription);

    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
